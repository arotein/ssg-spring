package com.youngjo.ssg.domain.user.controller;

import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;
import com.youngjo.ssg.domain.product.service.ProductService;
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
    private final ProductService productService;

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


    @GetMapping("/my/myPage")
    public CommonResponse myPageInfo() {
        return CommonResponse.builder()
                .data(userService.myPageInfo())
                .build();
    }

    // page, limit값만 받을 수 있음.
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my/like")
    public CommonResponse myLikePdtList(@Validated BoardSortFilterReqDto reqDto) {
        return CommonResponse.builder()
                .data(productService.getAllBoardPressedLike(reqDto.setDefault()))
                .build();
    }

    // ===== Dev Code =====
    @GetMapping("/test")
    public CommonResponse jenkinsBuildTest() {
        return CommonResponse.builder()
                .data("0조 백엔드 박찬우입니다 ^_^")
                .build();
    }
}