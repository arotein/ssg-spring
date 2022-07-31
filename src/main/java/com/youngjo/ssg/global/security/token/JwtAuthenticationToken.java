package com.youngjo.ssg.global.security.token;

import com.youngjo.ssg.global.security.auth.UserDetailsImpl;
import com.youngjo.ssg.global.security.dto.ClientInfoDto;
import com.youngjo.ssg.global.security.dto.SecurityLoginReqDto;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal; // UserDetailsImpl객체 저장
    private String jwt; // loginId -> 인증 -> encoding된 jwt를 저장
    private Object credentials;
    private final String SIGNER = System.getenv("SSG_JWT_SIGNER");

    public JwtAuthenticationToken(SecurityLoginReqDto loginReqDto) {
        super(null);
        this.principal = loginReqDto.getLoginId();
        this.credentials = loginReqDto.getPassword();
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserDetailsImpl userDetails) {
        super(userDetails.getAuthorities());
        this.principal = userDetails;
        this.jwt = generateJwt(userDetails);
        super.setAuthenticated(true);
    }

    private String generateJwt(UserDetailsImpl userDetails) {
        ClientInfoDto clientInfoDto = userDetails.getClientInfoDto();

        HMACSigner secretKey = HMACSigner.newSHA256Signer(SIGNER);
        JWT rawJwt = new JWT()
                .addClaim("server", "youngjo") // 토큰 생성자
                .addClaim("id", clientInfoDto.getId())
                .addClaim("name", clientInfoDto.getName())
                .addClaim("email", clientInfoDto.getEmail())
                .addClaim("role", clientInfoDto.getRole())
                .setExpiration(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).plusHours(2));
        // 요청헤더 Authorization : Bearer <JWT>
        return String.format("Bearer %s", JWT.getEncoder().encode(rawJwt, secretKey));
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
