package com.youngjo.ssg.domain.user.controller;

import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;
import com.youngjo.ssg.domain.user.service.UserService;
import com.youngjo.ssg.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public CommonResponse<String> signUp(@Validated @RequestBody SignUpReqDto dto) {
        userService.signUp(dto);
        return new CommonResponse<String>()
                .setData("회원가입이 완료되었습니다.");
    }

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ아래부터는 개발용 코드ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    @GetMapping("/user-list")
    public CommonResponse<List<User>> userList() {
        List<User> allUser = userService.findAllUser();
        return new CommonResponse<List<User>>()
                .setData(allUser);
    }
}
