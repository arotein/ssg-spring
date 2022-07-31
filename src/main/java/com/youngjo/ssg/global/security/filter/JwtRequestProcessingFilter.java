package com.youngjo.ssg.global.security.filter;

import com.youngjo.ssg.domain.user.enumeration.Role;
import com.youngjo.ssg.global.security.auth.UserDetailsImpl;
import com.youngjo.ssg.global.security.dto.ClientInfoDto;
import com.youngjo.ssg.global.security.token.JwtAuthenticationToken;
import io.fusionauth.jwt.InvalidJWTSignatureException;
import io.fusionauth.jwt.JWTExpiredException;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class JwtRequestProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private final String SIGNER = System.getenv("SSG_JWT_SIGNER");

    public JwtRequestProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public JwtRequestProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getHeader("Authentication") == null) {
            chain.doFilter(request, response);
        } else {
            try {
                String encodedJwt = req.getHeader("Authentication").split(" ")[1];
                Verifier verifier = HMACVerifier.newVerifier(SIGNER);
                JWT decodedJwt = JWT.getDecoder().decode(encodedJwt, verifier);

                Map<String, Object> allClaims = decodedJwt.getAllClaims();

                if (!allClaims.get("server").toString().equals("youngjo")) {
                    throw new InvalidJWTSignatureException();
                }
                ClientInfoDto clientInfoDto = new ClientInfoDto();

                clientInfoDto.setId(Long.parseLong(allClaims.get("id").toString()));
                clientInfoDto.setName(allClaims.get("name").toString());
                if (allClaims.get("email") != null) {
                    clientInfoDto.setEmail(allClaims.get("email").toString());
                }
                if (allClaims.get("role").toString().equals(Role.ROLE_NORMAL.toString())) {
                    clientInfoDto.setRole(Role.ROLE_NORMAL);
                }

                List<GrantedAuthority> roles = new ArrayList<>();
                roles.add(new SimpleGrantedAuthority(clientInfoDto.getRole().toString()));

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(new JwtAuthenticationToken(new UserDetailsImpl(clientInfoDto, roles)));
                SecurityContextHolder.setContext(context);
            } catch (NullPointerException npe) {
                log.error("NPE: {}", npe.getMessage());
            } catch (JWTExpiredException jwtEx) {
                // refresh token 요청
                // refresh token 인증 -> access token 재발급
                // <재발급 코드>
                // refresh token도 만료 -> 로그인 페이지 이동
                throw new AccountExpiredException("Expired Token.");
            } catch (InvalidJWTSignatureException jwtSigEx) {
                // 유효하지않은 토큰 -> 로그인 페이지로 이동
                log.error("Invalid Signature, class: {}", jwtSigEx.getClass());
            } catch (Exception exception) {
                log.error("Message: {}, class: {}", exception.getMessage(), exception.getClass());
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
