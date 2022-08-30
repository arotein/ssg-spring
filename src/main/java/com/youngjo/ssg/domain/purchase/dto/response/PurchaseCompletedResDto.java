package com.youngjo.ssg.domain.purchase.dto.response;

import com.youngjo.ssg.domain.purchase.domain.KakaoPayment;
import com.youngjo.ssg.domain.purchase.domain.UserPurchase;
import com.youngjo.ssg.domain.purchase.dto.PurchaseStaticDto;
import com.youngjo.ssg.global.common.AddressConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * 사용쿠폰, 포인트 정보, 지역별 추가 배송비 등은 생략
 */
@Getter
@NoArgsConstructor
public class PurchaseCompletedResDto {
    private String purchaseStatus;
    // == Purchase Info ==
    private String buyerName;
    private String buyerPhoneNumber;
    private String buyerEmail;
    private String refundWay;
    private String requestMessage;
    private String receiveWay;

    // == Product Info ==
    private List<PurchaseStaticDto.PurchaseCompletedPdtResDto> pdtList = new ArrayList<>();

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

    public PurchaseCompletedResDto(UserPurchase userPurchase) {
        this.purchaseStatus = userPurchase.getPurchaseStatus().getValue();
        this.buyerName = userPurchase.getBuyerName();
        this.buyerPhoneNumber = userPurchase.getBuyerPhoneNumber();
        this.buyerEmail = userPurchase.getBuyerEmail();
        this.refundWay = userPurchase.getRefundWay();
        this.requestMessage = userPurchase.getRequestMessage();
        this.receiveWay = userPurchase.getReceiveWay();

        this.pdtList.addAll(userPurchase.getPurchaseMiddleProductList().stream()
                .map(mid -> new PurchaseStaticDto.PurchaseCompletedPdtResDto(
                        mid.getMainProduct().getProductBoard().getMainImgPath(),
                        mid.getMainProduct().getProductBoard().getMainImgAlt(),
                        mid.getMainProduct().getId(),
                        mid.getMainProduct().getOptionValue1(),
                        mid.getMainProduct().getOptionValue2(),
                        mid.getMainProduct().getPrice(),
                        mid.getPdtQty()))
                .collect(Collectors.toList()));
        this.recipientName = userPurchase.getRecipientName();
        this.phoneNumber = userPurchase.getPhoneNumber();
        this.secondContactNumber = userPurchase.getSecondContactNumber();
        this.recipientAddress = AddressConverter.convertToString(userPurchase.getRecipientAddress());
        KakaoPayment kakaoPayment = userPurchase.getKakaoPayment();
        this.payment_method_type = kakaoPayment.getPayment_method_type();
        this.purchase_corp = kakaoPayment.getPurchase_corp();
        this.card_type = kakaoPayment.getCard_type();
        this.install_month = kakaoPayment.getInstall_month();
        this.interest_free_install = kakaoPayment.getInterest_free_install();
        this.total = kakaoPayment.getTotal();

        this.pdtList.forEach(e -> e.setListIndex(this.pdtList.indexOf(e)));
    }
}