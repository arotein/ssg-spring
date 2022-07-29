package com.youngjo.ssg.global.security.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class UserDetailsImpl extends User {
    private final com.youngjo.ssg.domain.user.domain.User user;

    public UserDetailsImpl(com.youngjo.ssg.domain.user.domain.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }
}
