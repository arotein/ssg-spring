package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpReqDto dto) {
        String rawPassword = dto.getPassword();
        // userId 검증
        String loginIdRegex = "^[\\da-zA-Z]{4,16}$";
        if (!dto.getLoginId().matches(loginIdRegex)) {
            throw new IllegalArgumentException("loginId는 4~16자의 숫자, 영문자만 가능합니다.");
        }
        // password 검증
        String passwordRegex = "^[\\w/[\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-+<>@\\#$%&\\\\\\=\\(\\'\\\"]]{8,40}$";
        if (!rawPassword.matches(passwordRegex)) {
            throw new IllegalArgumentException("password는 8~40자의 숫자, 영문자, 특수문자만 가능합니다.");
        }
        dto.setPassword(passwordEncoder.encode(rawPassword));

        // 임시 계정 생성
        userRepository.saveUser(dto.createUser());
    }

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ아래부터는 개발용 코드ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    @Override
    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }
}
