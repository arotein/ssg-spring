package com.youngjo.ssg.global.security.bean;

import com.youngjo.ssg.domain.user.enumeration.Role;
import com.youngjo.ssg.global.security.auth.UserDetailsImpl;
import com.youngjo.ssg.global.security.dto.ClientInfoDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/***
 * 로그인 상태 : 클라이언트의 계정 정보를 리턴
 * 비로그인 상태 : ClientInfoDto 객체대신 null을 리턴
 */
@Component
public class ClientInfoLoader {
    public boolean isAnonymous() {
        return getClientUser() == null;
    }

    public Long getUserId() {
        if (getClientUser() != null) {
            return getClientUser().getId();
        }
        return null;
    }

    public String getUserName() {
        if (getClientUser() != null) {
            return getClientUser().getName();
        }
        return null;
    }

    public String getUserEmail() {
        if (getClientUser() != null) {
            return getClientUser().getEmail();
        }
        return null;
    }

    public Role getUserRole() {
        if (getClientUser() != null) {
            return getClientUser().getRole();
        }
        return null;
    }

    private ClientInfoDto getClientUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) authentication.getPrincipal()).getClientInfoDto();
        }
        return null;
    }
}
