package com.youngjo.ssg.domain.purchase.dto.request;

import com.youngjo.ssg.domain.purchase.dto.PurchaseStaticDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/***
 * 사용쿠폰, 포인트 정보, 지역별 추가 배송비 등은 생략
 */
@Getter
@NoArgsConstructor
@ToString
public class PurchaseCompletedReqDto {
    // == Purchase Info ==
    private String buyerName;
    private String buyerPhoneNumber;
    private String buyerEmail;
    private String refundWay;
    private String requestMessage;
    private String receiveWay;

    // == Delivery Address ==
    private Long myDeliAddrId;

    // == Pdt Info ==
    private Map<Long, Integer> pdtIdMap; // <pdtId, pdtQty>

    // == Payment ==
    private PurchaseStaticDto.KakaoPaymentReqDto kakaoPayment; // -> 값 검증 불필요. 일단 엔티티로 바로 받기
}