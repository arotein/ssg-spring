package com.youngjo.ssg.domain.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.user.domain.Address;
import com.youngjo.ssg.domain.user.domain.MyDeliveryAddress;
import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.enumeration.PurchaseStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/***
 * 할인, 포인트, 적립은 생략
 * 받는 사람 배송지는 DeliveryAddress에서 찾기
 * refundWay : 환불 방법
 * requestMessage : 배송 요청사항
 * recipientName : 수령인 이름
 * phoneNumber : 수령인 휴대폰 010-0000-0000
 * secondContactNumber : 두번째 연락처 00(또는 000)-000(또는 0000)-0000
 * recipientAddress : 배송지
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserPurchase extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    // == Purchase Info ==
    private String buyerName;
    private String buyerPhoneNumber;
    private String buyerEmail;
    private String refundWay;
    private String requestMessage;
    private String receiveWay;

    // == Delivery Address ==
    private String recipientName;
    private String phoneNumber;
    private String secondContactNumber;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address recipientAddress;

    // == Payment ==
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "kakao_payment_id")
    private KakaoPayment kakaoPayment;

    // == etc. Mapping ==
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "userPurchase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchaseMiddleProduct> purchaseMiddleProductList = new ArrayList<>();

    @Builder
    public UserPurchase(String buyerName, String buyerPhoneNumber, String buyerEmail, String refundWay, String requestMessage, String receiveWay, String recipientName, String phoneNumber, String secondContactNumber, Address recipientAddress) {
        this.purchaseStatus = PurchaseStatus.PROCEEDING;
        this.buyerName = buyerName;
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.buyerEmail = buyerEmail;
        this.refundWay = refundWay;
        this.requestMessage = requestMessage;
        this.receiveWay = receiveWay;
        this.recipientName = recipientName;
        this.phoneNumber = phoneNumber;
        this.secondContactNumber = secondContactNumber;
        this.recipientAddress = recipientAddress;
    }

    public UserPurchase linkToUser(User user) {
        this.user = user;
        user.linkToUserPurchase(this);
        return this;
    }

    public UserPurchase linkToKakaoPayment(KakaoPayment kakaoPayment) {
        this.kakaoPayment = kakaoPayment;
        kakaoPayment.linkToUserPurchase(this);
        return this;
    }

    public UserPurchase linkToPurchaseMiddleProduct(PurchaseMiddleProduct purchaseMiddleProduct) {
        this.purchaseMiddleProductList.add(purchaseMiddleProduct);
        return this;
    }

    public UserPurchase setRecipientAddressFromMyDeliAddr(MyDeliveryAddress myDeliveryAddress) {
        this.recipientName = myDeliveryAddress.getRecipientName();
        this.phoneNumber = myDeliveryAddress.getPhoneNumber();
        this.secondContactNumber = myDeliveryAddress.getSecondContactNumber();
        Address addr = myDeliveryAddress.getRecipientAddress();
        this.recipientAddress = Address.builder()
                .city(addr.getCity())
                .street(addr.getStreet())
                .detail(addr.getDetail())
                .postalCode(addr.getPostalCode())
                .build();
        return this;
    }

    public UserPurchase linkToRecipientAddress(Address address) {
        this.recipientAddress = address;
        return this;
    }
}