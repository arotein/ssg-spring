package com.youngjo.ssg.domain.user.dto.request;

import com.youngjo.ssg.domain.product.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SignUpReqDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNum;
    private Address address;

    public SignUpReqDto setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
        return this;
    }
}
