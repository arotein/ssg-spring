package com.youngjo.ssg.global.security.bean;

import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.global.security.auth.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/***
 * 로그인 상태 : 클라이언트의 계정 정보를 리턴
 * 비로그인 상태 : User객체대신 null을 리턴
 */
@Component
public class ClientUserLoader {
    public User getClientUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        }
        return null;
    }

    public boolean isAnonymous() {
        if (getClientUser() == null) {
            return true;
        }
        return false;
    }
}
