package com.youngjo.ssg.domain.user.dto.request;

import com.youngjo.ssg.domain.user.domain.Address;
import com.youngjo.ssg.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class SignUpReqDto {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @Email
    private String email;
    private String phone;
    private Address address;

    public User createUser() {
        return User.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .address(address)
                .build();
    }
}
