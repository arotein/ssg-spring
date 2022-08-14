package com.youngjo.ssg.domain.user.controller;

import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;
import com.youngjo.ssg.domain.user.service.UserService;
import com.youngjo.ssg.global.common.CommonResponse;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ClientInfoLoader clientInfoLoader;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signUp")
    public CommonResponse<String> signUp(@Validated @RequestBody SignUpReqDto dto) {
        log.info("/api/signUp request");
        userService.signUp(dto);
        return new CommonResponse<String>()
                .setData("회원가입이 완료되었습니다.");
    }

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ아래부터는 개발용 코드ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user-list")
    public CommonResponse<List<User>> userList() {
        System.out.println("Controller user-list 잘 작동됨");
        List<User> allUser = userService.findAllUser();
        return new CommonResponse<List<User>>()
                .setData(allUser);
    }

    //    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/user-lists")
    public CommonResponse<List<User>> userLists() {
        System.out.println("Controller user-lists 잘 작동됨");
        List<User> allUser = userService.findAllUser();
        return new CommonResponse<List<User>>()
                .setData(allUser);
    }
}
