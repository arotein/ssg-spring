package com.youngjo.ssg.domain.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    public static class DeliveryAddrDto {
        private Long id;
        private String alias;
        private String recipientName;
        private String phoneNumber;
        private String address;
    }
}
