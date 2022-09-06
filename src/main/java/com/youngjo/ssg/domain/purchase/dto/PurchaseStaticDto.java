package com.youngjo.ssg.domain.purchase.dto;

import lombok.*;

public class PurchaseStaticDto {
    @Data
    public static class BoardResDto {
        private Integer listIndex;
        private Long boardId;
        private String title;
        private String brand;
        private String salesSite;

        private Boolean isEachShippingFee;
        private Integer shippingFee;
        private Integer shippingFreeOver;

        private String thumbImg;
        private String thumbImgAlt;

        private Long pdtId;
        private String optionValue1;
        private String optionValue2;
        private Long price;

        public BoardResDto(Long boardId, String title, String brand, String salesSite, Boolean isEachShippingFee, Integer shippingFee, Integer shippingFreeOver, String thumbImg, String thumbImgAlt, Long pdtId, String optionValue1, String optionValue2, Long price) {
            this.boardId = boardId;
            this.title = title;
            this.brand = brand;
            this.salesSite = salesSite;
            this.isEachShippingFee = isEachShippingFee;
            this.shippingFee = shippingFee;
            this.shippingFreeOver = shippingFreeOver;
            this.thumbImg = thumbImg;
            this.thumbImgAlt = thumbImgAlt;
            this.pdtId = pdtId;
            this.optionValue1 = optionValue1;
            this.optionValue2 = optionValue2;
            this.price = price;
        }
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
    @ToString
    public static class KakaoPaymentReqDto {
        private String aid;
        private String tid;
        private String cid;
        private String partner_order_id;
        private String partner_user_id;
        private String payment_method_type;
        private String item_name;
//        private String item_code;
        private Integer quantity;
        private String created_at;
        private String approved_at;
//        private String payload;
        private KakaoPaymentAmountReqDto amount;
        private KakaoPaymentCardInfoReqDto card_info;
    }

    @Getter
    @NoArgsConstructor
    @ToString
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
    @ToString
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
//        private String card_item_code;
    }

    @Data
    public static class PurchaseCompletedPdtResDto {
        private Integer listIndex;
        private String thumbImg;
        private String thumbImgAlt;
        private Long pdtId;
        private String optionValue1;
        private String optionValue2;
        private Long price;
        private Integer qty;

        public PurchaseCompletedPdtResDto(String thumbImg, String thumbImgAlt, Long pdtId, String optionValue1, String optionValue2, Long price, Integer qty) {
            this.thumbImg = thumbImg;
            this.thumbImgAlt = thumbImgAlt;
            this.pdtId = pdtId;
            this.optionValue1 = optionValue1;
            this.optionValue2 = optionValue2;
            this.price = price;
            this.qty = qty;
        }
    }
}
