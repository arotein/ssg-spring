package com.youngjo.ssg.domain.purchase.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/***
 * 사용쿠폰, 포인트 정보, 지역별 추가 배송비 등은 생략
 */
@Getter
@NoArgsConstructor
public class PurchaseCompletedResDto {
    private String nowDeliveryStatus;
    // == Purchase Info ==
    private String buyerName;
    private String buyerPhoneNumber;
    private String buyerEmail;
    private String refundWay;
    private String requestMessage;
    private String receiveWay;

    // == Product Info ==
    // 생략
    
    // == Delivery Address ==
    private String recipientName;
    private String phoneNumber;
    private String secondContactNumber;
    private String recipientAddress; // -> String

    // == Payment ==
    private String payment_method_type;
    private String purchase_corp;
    private String card_type;
    private String install_month;
    private String interest_free_install;
    private Integer total;
}