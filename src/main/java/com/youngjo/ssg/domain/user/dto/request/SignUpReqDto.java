package com.youngjo.ssg.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class SignUpReqDto {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordConfirm;
    @NotEmpty
    private String name;
    @Email
    private String email;
    private String phoneNum;
//    private Address address;

    public SignUpReqDto setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
        return this;
    }
}
