package com.youngjo.ssg.domain.user.controller;

import com.youngjo.ssg.domain.user.dto.request.CheckDuplicate;
import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;
import com.youngjo.ssg.domain.user.service.UserService;
import com.youngjo.ssg.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signUp")
    public CommonResponse<String> signUp(@Validated @RequestBody SignUpReqDto dto) {
        userService.signUp(dto);
        return new CommonResponse<String>()
                .setData("회원가입이 완료되었습니다.");
    }

    // true: 중복임, false: 중복아님 -> 사용가능
    @PostMapping("/duplCheck/loginId")
    public Boolean checkForLoginIdDuplicates(@Validated @RequestBody CheckDuplicate checkDuplicate) {
        return userService.checkForLoginIdDuplicate(checkDuplicate.getLoginId());
    }
}