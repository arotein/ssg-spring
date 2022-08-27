package com.youngjo.ssg.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CheckLoginIdDuplicateReqDto {
    @NotBlank
    private String loginId;
}
