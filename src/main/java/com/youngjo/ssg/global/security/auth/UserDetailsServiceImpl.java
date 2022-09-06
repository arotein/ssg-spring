package com.youngjo.ssg.global.security.auth;

import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import com.youngjo.ssg.global.enumeration.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findUserByLoginId(loginId);
        // 계정 검증
        if (user == null) {
            log.warn("Login request: ID does not exist or incorrect password");
            throw new UsernameNotFoundException("존재하지 않는 아이디 혹은 잘못된 비밀번호");
        }
        if (user.getStatus() == UserStatus.BANNED) {
            log.warn("Login request: Account not available");
            throw new LockedException("사용할 수 없는 계정");
        }
        if (user.getStatus() == UserStatus.DISABLED) {
            log.warn("Login request: Temporarily disabled account");
            throw new DisabledException("일시적으로 비활성화된 계정");
        }
        // 계정 권한
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new UserDetailsImpl(user, roles);
    }
}
