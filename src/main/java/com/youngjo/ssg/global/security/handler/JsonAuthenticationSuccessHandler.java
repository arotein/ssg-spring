package com.youngjo.ssg.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.global.common.CommonResponse;
import com.youngjo.ssg.global.security.bean.ClientUserLoader;
import com.youngjo.ssg.global.security.dto.SecurityLoginResDto;
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
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;
    private final ClientUserLoader clientUserLoader;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User clientUser = clientUserLoader.getClientUser();
        CommonResponse<SecurityLoginResDto> comRes = new CommonResponse<>();
        comRes.setData(new SecurityLoginResDto(clientUser.getName(), clientUser.getEmail()));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), comRes);
    }
}
