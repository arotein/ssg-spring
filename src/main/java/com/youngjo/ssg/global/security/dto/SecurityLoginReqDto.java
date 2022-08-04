package com.youngjo.ssg.global.security.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SecurityLoginReqDto {
    @NotNull
    private String loginId;
    @NotEmpty
    private String password;
}
