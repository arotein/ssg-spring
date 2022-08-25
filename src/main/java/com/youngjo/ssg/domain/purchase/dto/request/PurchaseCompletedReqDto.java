package com.youngjo.ssg.domain.purchase.dto.request;

import com.youngjo.ssg.domain.purchase.domain.KakaoPayment;
import com.youngjo.ssg.domain.user.dto.UserStaticDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/***
 * 사용쿠폰, 포인트 정보, 지역별 추가 배송비 등은 생략
 * Payment정보 일부 생략
 */
@Getter
@NoArgsConstructor
public class PurchaseCompletedReqDto {
    // == Purchase Info ==
    private String buyerName;
    private String buyerPhoneNumber;
    private String buyerEmail;
    private String refundWay;
    private String requestMessage;
    private String receiveWay;

    // == Pdt Info ==
    private Map<Long, Integer> pdtIdMap; // <pdtId, pdtQty>


    // == Delivery Address ==
    private String recipientName;
    private String phoneNumber;
    private String secondContactNumber;
    private UserStaticDto.AddressReqDto recipientAddressReqDto;

    // == Payment ==
    private KakaoPayment kakaoPayment;
}