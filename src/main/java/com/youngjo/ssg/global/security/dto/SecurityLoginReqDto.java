package com.youngjo.ssg.global.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SecurityLoginReqDto {
    @NotNull
    private String loginId;
    @NotEmpty
    private String password;
}
