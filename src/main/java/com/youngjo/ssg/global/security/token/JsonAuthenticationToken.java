package com.youngjo.ssg.global.security.token;

import com.youngjo.ssg.global.security.auth.UserDetailsImpl;
import com.youngjo.ssg.global.security.dto.SecurityLoginReqDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JsonAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public JsonAuthenticationToken(SecurityLoginReqDto loginReqDto) {
        super(loginReqDto.getLoginId(), loginReqDto.getPassword());
    }

    public JsonAuthenticationToken(UserDetailsImpl userDetails) {
        super(userDetails, null, userDetails.getAuthorities());
    }
}
