package com.youngjo.ssg.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngjo.ssg.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JsonLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CommonResponse<String> comRes = new CommonResponse<>();
        comRes.setData("로그아웃에 성공하였습니다.");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), comRes);
    }
}
