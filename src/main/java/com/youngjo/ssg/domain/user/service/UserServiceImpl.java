package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.domain.Address;
import com.youngjo.ssg.domain.user.domain.MyDeliveryAddress;
import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpReqDto dto) {
        String rawPassword = dto.getPassword();
        if (rawPassword == null || !rawPassword.matches("^[\\w/\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-+<>@\\#$%&\\\\\\=\\(\\'\\\"]{8,40}$")) {
            throw new IllegalArgumentException("Invalid password");
        }

        // 임시 계정 생성
        User user = User.builder()
                .loginId(dto.getLoginId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNum())
                .build()
                .linkToAddress(Address.builder()
                        .city(dto.getAddress().getCity())
                        .street(dto.getAddress().getStreet())
                        .detail(dto.getAddress().getDetail())
                        .postalCode(dto.getAddress().getPostalCode())
                        .build()
                );

        MyDeliveryAddress deliveryAddr = MyDeliveryAddress.builder()
                .isMain(true)
                .alias(user.getName())
                .recipientName(user.getName())
                .phoneNumber(user.getPhoneNumber())
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
