package com.youngjo.ssg.domain.purchase.controller;

import com.youngjo.ssg.domain.purchase.dto.request.PurchaseCompletedReqDto;
import com.youngjo.ssg.domain.purchase.dto.request.PurchaseProceedReqDto;
import com.youngjo.ssg.domain.purchase.service.UserPurchaseService;
import com.youngjo.ssg.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase/user")
@RequiredArgsConstructor
public class UserPurchaseController {
    private final UserPurchaseService userPurchaseService;

    // 결제 진행창 -> 장바구니로 접속 or 바로구매로 접속
    // response dto 최적화 생략
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/proceed")
    public CommonResponse proceedToPayment(@Validated @RequestBody PurchaseProceedReqDto reqDto) {
        return CommonResponse.builder()
                .data(userPurchaseService.proceedToPayment(reqDto))
                .build();
    }

    // 결제완료 시 결제정보+사용쿠폰정보+사용포인트정보+구매자+배송지+구매상품,상품Qty,배송요청사항 요청
    // -> 결제완료창 응답과 동시에 비동기로 DB에 저장
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/completed")
    public CommonResponse completedPayment(@Validated @RequestBody PurchaseCompletedReqDto reqDto) {
        return CommonResponse.builder()
                .data(userPurchaseService.completedToPayment(reqDto))
                .build();
    }

    // 유저 구매목록 조회 -> 기간별 sort

    // 단건 결제 상세정보 조회
}
