package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.domain.DeliveryAddress;
import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final ClientInfoLoader clientInfoLoader;
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
        String passwordRegex = "^[\\w/\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-+<>@\\#$%&\\\\\\=\\(\\'\\\"]{8,40}$";
        if (!rawPassword.matches(passwordRegex)) {
            throw new IllegalArgumentException("password는 8~40자의 숫자, 영문자, 특수문자만 가능합니다.");
        }
        dto.setEncodedPassword(passwordEncoder.encode(rawPassword));
        // 전화번호 검증
        String phoneNumRegex = "^010-\\d{3,4}-\\d{3,4}$";
        if (!dto.getPhoneNum().matches(phoneNumRegex)) {
            throw new IllegalArgumentException("휴대폰 번호는 010-[3~4자리]-[3~4자리]로만 가능합니다.");
        }
        // 주소 검증 생략

        // 임시 계정 생성
        User user = User.builder()
                .loginId(dto.getLoginId())
                .password(dto.getPassword())
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNum())
                .address(dto.getAddress())
                .build()
                .linkToAddress(dto.getAddress());

        DeliveryAddress deliveryAddr = DeliveryAddress.builder()
                .alias(user.getName())
                .recipientName(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .isMain(true)
                .build()
                .linkToRecipientAddress(user.getAddress())
                .linkToUser(user);

        userRepository.saveUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkForLoginIdDuplicate(String loginId) {
        return userRepository.findUserByLoginId(loginId) != null ? true : false;
    }

    @Override
    public void updateLastAccessTime(Long id) {
        userRepository.findUserById(id).updateLastAccessTime();
    }
}
