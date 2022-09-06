package com.youngjo.ssg.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngjo.ssg.domain.user.service.UserService;
import com.youngjo.ssg.global.common.CommonResponse;
import com.youngjo.ssg.global.security.auth.UserDetailsImpl;
import com.youngjo.ssg.global.security.token.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader("Authentication", token.getJwt());
        objectMapper.writeValue(response.getWriter(), CommonResponse.builder().data(true).build());

        // 로그인 시간 갱신
        UserDetailsImpl userDetails = (UserDetailsImpl) token.getPrincipal();
        userService.updateLastAccessTime(userDetails.getId());
    }
}
