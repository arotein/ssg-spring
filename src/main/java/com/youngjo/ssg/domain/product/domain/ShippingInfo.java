package com.youngjo.ssg.domain.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class ShippingInfo {
    // true(1), false(0)
    private Boolean isEachShippingFee; // 배송비 개당 부과?
    private Boolean isPremium; // 프리미엄 배송
    private Boolean isCrossBorderShipping; // 해외직구

    private Integer shippingFee; // 3000원 선택 or 0원(무료)
    private Integer shippingFeeJeju; // 제주 추가금
    private Integer shippingFeeIsland; // 도서산간 추가금
    private Integer shippingFreeOver; // ~원 이상 무료배송, 10원 이상 or null

    private String courierCompany; // 택배사
    private String deliveryDate; // 배송일자(10일 이내 도착, 8/11(목) 도착예정), Unix Time
}
