package com.youngjo.ssg.domain.user.controller;

import com.youngjo.ssg.domain.user.dto.request.PdtInCartReqDto;
import com.youngjo.ssg.domain.user.dto.response.PdtInCartResDto;
import com.youngjo.ssg.domain.user.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public List<PdtInCartResDto> anonymousCartList(@RequestBody List<Long> pdtIdList) {
        log.info("POST /api/cart request");
        return cartService.getCartPdtListByPdtIds(pdtIdList);
    }

    // 로그인유저 상품 카트에 담기, 비로그인 카트 -> 로그인 카트로 이동시 list필요
    // 비로그인은 프론트에서 로컬스토리지에 pdtId값만 저장해놨다가 리스트 요청하기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addPdt")
    public Boolean addProductToCart(@RequestBody List<PdtInCartReqDto> pdtDtoList) {
        log.info("/api/cart/addPdt request");
        cartService.addProductToCart(pdtDtoList);
        return true;
    }

    // 로그인유저 카트에 담긴 상품정보 리턴
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public List<PdtInCartResDto> userCartList() {
        log.info("GET /api/cart request");
        return cartService.getUserPdtListInCart();
    }

    // 품절 상품 삭제
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/soldOutPdt")
    public Boolean delSoldOutPdtInUserCart() {
        log.info("DELETE /api/cart/soldOutPdt request");
        cartService.delSoldOutPdtInUserCart();
        return true;
    }

    // 선택 상품 삭제
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/pdt")
    public Boolean delPdtInUserCart(@RequestBody List<Long> pdtIdList) {
        log.info("DELETE /api/cart/pdt request");
        cartService.delPdtInUserCart(pdtIdList);
        return true;
    }

    // 선택 상품 수정
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/pdt")
    public Boolean modifyPdtInUserCart(@RequestBody PdtInCartReqDto pdtDto) {
        log.info("PUT /api/cart/pdt request");
        cartService.modifyPdtInUserCart(pdtDto);
        return true;
    }
}
