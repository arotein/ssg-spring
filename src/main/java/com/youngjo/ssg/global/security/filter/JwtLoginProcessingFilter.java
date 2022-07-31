package com.youngjo.ssg.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngjo.ssg.global.security.dto.SecurityLoginReqDto;
import com.youngjo.ssg.global.security.token.JwtAuthenticationToken;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper;

    public JwtLoginProcessingFilter(String processUrl) {
        super(new AntPathRequestMatcher(processUrl, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        // request type 검증
        if (!isJson(request) || !request.getMethod().equalsIgnoreCase("POST")) {
            throw new AuthenticationServiceException("JSON과 POST타입만 지원됩니다.");
        }

        SecurityLoginReqDto loginReqDto = objectMapper.readValue(request.getReader(), SecurityLoginReqDto.class);
        // request parameter 검증
        if (!StringUtils.hasText(loginReqDto.getLoginId()) || !StringUtils.hasText(loginReqDto.getPassword())) {
            throw new IllegalArgumentException("email 또는 password가 공백입니다.");
        }

        // 임시token 생성
        JwtAuthenticationToken tempToken = new JwtAuthenticationToken(loginReqDto);
        Authentication formalToken = getAuthenticationManager().authenticate(tempToken);
        tempToken.eraseCredentials();
        return formalToken;
    }

    private boolean isJson(HttpServletRequest request) {
        String headerType = request.getHeader("Content-Type").toLowerCase();
        return headerType.contains(MediaType.APPLICATION_JSON_VALUE);
    }

    public void setObjectMapper(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }
}
