package com.youngjo.ssg.global.security.provider;

import com.youngjo.ssg.global.security.auth.UserDetailsImpl;
import com.youngjo.ssg.global.security.token.JsonAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String rawPassword = (String) authentication.getCredentials();

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        return new JsonAuthenticationToken(userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JsonAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
