package com.youngjo.ssg.domain.user.controller;

import com.youngjo.ssg.domain.user.dto.request.PdtInCartReqDto;
import com.youngjo.ssg.domain.user.dto.response.PdtInCartResDto;
import com.youngjo.ssg.domain.user.service.CartService;
import com.youngjo.ssg.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // 익명유저 카트에 담긴 상품정보 리턴
    @PreAuthorize("isAnonymous()")
    @PostMapping("")
    public CommonResponse anonymousCartList(@RequestBody List<Long> pdtIdList) {
        List<PdtInCartResDto> list = cartService.getCartPdtListByPdtIds(pdtIdList);
        list.forEach(e -> e.setListIndex(list.indexOf(e)));
        return CommonResponse.builder()
                .data(list)
                .build();
    }

    // 로그인유저 상품 카트에 담기, 비로그인 카트 -> 로그인 카트로 이동시 list필요
    // 비로그인은 프론트에서 로컬스토리지에 pdtId값만 저장해놨다가 리스트 요청하기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addPdt")
    public CommonResponse addProductToCart(@Validated @RequestBody List<PdtInCartReqDto> pdtDtoList) {
        cartService.addProductToCart(pdtDtoList);
        return CommonResponse.builder()
                .data(true)
                .build();
    }

    // 로그인유저 카트에 담긴 상품정보 리턴
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public CommonResponse userCartList() {
        List<PdtInCartResDto> list = cartService.getUserPdtListInCart();
        list.forEach(e -> e.setListIndex(list.indexOf(e)));
        return CommonResponse.builder()
                .data(list)
                .build();
    }

    // 품절 상품 삭제
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/soldOutPdt")
    public CommonResponse delSoldOutPdtInUserCart() {
        cartService.delSoldOutPdtInUserCart();
        return CommonResponse.builder()
                .data(true)
                .build();
    }

    // 선택 상품 삭제
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/pdt/{pdtId}")
    public CommonResponse delPdtInUserCart(@PathVariable Long pdtId) {
        cartService.delPdtInUserCart(pdtId);
        return CommonResponse.builder()
                .data(true)
                .build();
    }

    // 선택 상품 수정
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/pdt")
    public CommonResponse updatePdtInUserCart(@Validated @RequestBody PdtInCartReqDto pdtDto) {
        return CommonResponse.builder()
                .data(cartService.updatePdtInUserCart(pdtDto))
                .build();
    }
}
