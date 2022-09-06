package com.youngjo.ssg.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngjo.ssg.global.common.CommonResponse;
import com.youngjo.ssg.global.enumeration.Role;
import com.youngjo.ssg.global.security.auth.UserDetailsImpl;
import com.youngjo.ssg.global.security.dto.ClientInfoDto;
import com.youngjo.ssg.global.security.token.JwtAuthenticationToken;
import io.fusionauth.jwt.InvalidJWTException;
import io.fusionauth.jwt.InvalidJWTSignatureException;
import io.fusionauth.jwt.JWTExpiredException;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

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
    public JwtRequestProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getHeader("Authentication") == null) {
            chain.doFilter(req, res);
        } else {
            try {
                String encodedJwt = req.getHeader("Authentication").split(" ")[1];
                Verifier verifier = HMACVerifier.newVerifier(JwtAuthenticationToken.SIGNER);
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
                chain.doFilter(request, response);
            } catch (NullPointerException npe) {
                log.warn("NPE: {}", npe.getMessage());
            } catch (JWTExpiredException jwtEx) {
                log.warn("Expired Token, class: {}", jwtEx.getClass());
                // 만료된 토큰
                // refresh token 요청
                // refresh token 인증 -> access token 재발급
                // <재발급 코드>
                // refresh token도 만료 -> 로그인 페이지 이동
                ObjectMapper objectMapper = new ObjectMapper();
                res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                res.setStatus(HttpStatus.UNAUTHORIZED.value());
                objectMapper.writeValue(res.getWriter(),
                        CommonResponse.builder().errorCode(1).errorMessage("Expired Token").build());
            } catch (InvalidJWTSignatureException jwtSigEx) {
                log.warn("Invalid Signature Token, class: {}", jwtSigEx.getClass());
                chain.doFilter(req, res);
            } catch (InvalidJWTException exception) {
                log.warn("Invalid Token, class: {}", exception.getClass());
                chain.doFilter(req, res);
            } catch (ArrayIndexOutOfBoundsException exception) {
                log.warn("Invalid Token, class: {}", exception.getClass());
                chain.doFilter(req, res);
            } catch (Exception exception) {
                log.error("Message: {}, class: {}", exception.getMessage(), exception.getClass());
                chain.doFilter(req, res);
            }
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
