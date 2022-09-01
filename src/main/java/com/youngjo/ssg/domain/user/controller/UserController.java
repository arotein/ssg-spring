package com.youngjo.ssg.domain.user.controller;

import com.youngjo.ssg.domain.user.dto.request.CheckEmailDuplicateReqDto;
import com.youngjo.ssg.domain.user.dto.request.CheckLoginIdDuplicateReqDto;
import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;
import com.youngjo.ssg.domain.user.service.UserService;
import com.youngjo.ssg.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signUp")
    public CommonResponse signUp(@Validated @RequestBody SignUpReqDto dto) {
        return CommonResponse.builder()
                .data(userService.signUp(dto))
                .build();
    }

    // true: 중복임, false: 중복아님 -> 사용가능
    @PostMapping("/duplCheck/loginId")
    public CommonResponse checkForLoginIdDuplicates(@Validated @RequestBody CheckLoginIdDuplicateReqDto reqDto) {
        return CommonResponse.builder()
                .data(userService.checkForLoginIdDuplicate(reqDto.getLoginId()))
                .build();
    }

    @PostMapping("/duplCheck/email")
    public CommonResponse checkForEmailDuplicates(@Validated @RequestBody CheckEmailDuplicateReqDto reqDto) {
        return CommonResponse.builder()
                .data(userService.checkForEmailDuplicates(reqDto.getEmail()))
                .build();
    }

    // ==== Dev Code ====
    @GetMapping("/user/test")
    public CommonResponse test() {
        return CommonResponse.builder()
                .data("헤으응 ㅠㅠ")
                .build();
    }
}