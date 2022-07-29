package com.youngjo.ssg.global.security.auth;

import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import com.youngjo.ssg.global.enumeration.Status;
import lombok.RequiredArgsConstructor;
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
            throw new UsernameNotFoundException("존재하지 않는 계정입니다.");
        }
        if (user.getStatus() == Status.BANNED) {
            throw new LockedException("사용할 수 없는 계정입니다. 관리자에게 문의하세요.");
        }
        if (user.getStatus() == Status.DISABLED) {
            throw new DisabledException("일시적으로 비활성화된 계정입니다.");
        }
        // 계정 권한
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new UserDetailsImpl(user, roles);
    }
}
