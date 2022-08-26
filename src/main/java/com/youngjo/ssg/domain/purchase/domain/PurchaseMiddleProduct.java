package com.youngjo.ssg.domain.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.product.domain.MainProduct;
import com.youngjo.ssg.global.enumeration.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/***
 * UserPurchase와 MainProduct의 중간테이블 -> 구매한 상품에 대한 상태관리
 * 나중에 리뷰, 별점 추가하기
 * deliveryCorp : 배송업체
 * trackingNumber : 운송장 번호
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PurchaseMiddleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_middle_product_id")
    private Long id;
    private Integer pdtQty;
    // == Delivery Info ==
    @Enumerated(EnumType.STRING)
    private DeliveryStatus nowDeliveryStatus;
    @ElementCollection
    private Map<DeliveryStatus, Timestamp> deliveryHistory = new HashMap<>();
    private String deliveryCorp; // enum만들기
    private String trackingNumber;

    // == Mapping ==
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_purchase_id")
    private UserPurchase userPurchase;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_product_id")
    private MainProduct mainProduct;

    @Builder
    public PurchaseMiddleProduct(Integer pdtQty) {
        this.nowDeliveryStatus = DeliveryStatus.CHECKING_PAYMENT;
        this.deliveryHistory.put(DeliveryStatus.CHECKING_PAYMENT, new Timestamp(System.currentTimeMillis()));
        this.pdtQty = pdtQty;
    }

    public PurchaseMiddleProduct startShipping(String deliveryCorp, String trackingNumber) {
        this.deliveryCorp = deliveryCorp;
        this.trackingNumber = trackingNumber;
        this.nowDeliveryStatus = DeliveryStatus.START_SHIPPING;
        this.deliveryHistory.put(DeliveryStatus.START_SHIPPING, new Timestamp(System.currentTimeMillis()));
        return this;
    }

    public PurchaseMiddleProduct linkToUserPurchase(UserPurchase userPurchase) {
        this.userPurchase = userPurchase;
        userPurchase.linkToPurchaseMiddleProduct(this);
        return this;
    }

    public PurchaseMiddleProduct linkToMainProduct(MainProduct mainProduct) {
        this.mainProduct = mainProduct;
        mainProduct.linkToPurchaseMiddleProduct(this);
        return this;
    }

    public PurchaseMiddleProduct returnThis() {
        return this;
    }
}
