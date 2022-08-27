package com.youngjo.ssg.domain.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

public class PurchaseStaticDto {
    @Getter
    @AllArgsConstructor
    public static class BoardResDto {
        private Long boardId;
        private String title;
        private String brand;
        private String salesSite;

        private Boolean isEachShippingFee;
        private Integer shippingFee;
        private Integer shippingFreeOver;

        private String thumbImg;

        private Long pdtId;
        private String optionValue1;
        private String optionValue2;
        private Long price;
    }

    @Getter
    @AllArgsConstructor
    public static class MyDeliAddrDto {
        private Long id;
        private String alias;
        private String recipientName;
        private String phoneNumber;
        private String address;
    }

    @Getter
    @NoArgsConstructor
    public static class KakaoPaymentReqDto {
        private String aid;
        private String tid;
        private String cid;
        private String sid;
        private String partner_order_id;
        private String partner_user_id;
        private String payment_method_type;
        private String item_name;
        private String item_code;
        private Integer quantity;
        private Timestamp created_at; // Json 직렬화
        private Timestamp approved_at; // Json 직렬화
        private String payload;
        private KakaoPaymentAmountReqDto amount;
        private KakaoPaymentCardInfoReqDto card_info;
    }

    @Getter
    @NoArgsConstructor
    public static class KakaoPaymentAmountReqDto {
        private Integer total;
        private Integer tax_free;
        private Integer vat;
        private Integer point;
        private Integer discount;
        private Integer green_deposit;
    }

    @Getter
    @NoArgsConstructor
    public static class KakaoPaymentCardInfoReqDto {
        private String purchase_corp;
        private String purchase_corp_code;
        private String issuer_corp;
        private String issuer_corp_code;
        private String kakaopay_purchase_corp;
        private String kakaopay_purchase_corp_code;
        private String kakaopay_issuer_corp;
        private String kakaopay_issuer_corp_code;
        private String bin;
        private String card_type;
        private String install_month;
        private String approved_id;
        private String card_mid;
        private String interest_free_install;
        private String card_item_code;
    }

    @Getter
    @AllArgsConstructor
    public static class PurchaseCompletedPdtResDto {
        private String thumbImg;
        private String thumbImgAlt;
        private Long pdtId;
        private String optionValue1;
        private String optionValue2;
        private Long price;
        private Integer qty;
    }
}
