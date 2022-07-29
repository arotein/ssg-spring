package com.youngjo.ssg.domain.user.dto.request;

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
    private String address;

    public User createUser() {
        User user = new User();
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        return user;
    }
}
