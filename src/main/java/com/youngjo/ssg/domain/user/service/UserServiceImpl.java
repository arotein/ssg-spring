package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.domain.Address;
import com.youngjo.ssg.domain.user.domain.MyDeliveryAddress;
import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
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
    public Boolean signUp(SignUpReqDto dto) {
        String rawPassword = dto.getPassword();
        if (rawPassword == null || !rawPassword.matches("^[\\w/\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-+<>@\\#$%&\\\\\\=\\(\\'\\\"]{8,40}$")) {
            throw new IllegalArgumentException("Invalid password");
        }

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
        try {
            userRepository.saveUser(user);
        } catch (ConstraintViolationException ex) {
            throw new IllegalArgumentException("중복된 ID 또는 Email 또는 Phone Number가 있습니다.");
        }
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkForLoginIdDuplicate(String loginId) {
        return userRepository.findUserByLoginId(loginId) != null ? true : false;
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkForEmailDuplicates(String email) {
        return userRepository.findUserByEmail(email) != null ? true : false;
    }

    @Override
    public void updateLastAccessTime(Long id) {
        userRepository.findUserById(id).updateLastAccessTime();
    }
}
