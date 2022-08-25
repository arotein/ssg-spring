package com.youngjo.ssg.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/***
 * 주문 배송지
 * -> 상품구매시 or 유저의 배송지 관리
 * isMain : 기본 배송지 유무
 * alias : 주소 별칭
 * recipientName : 수령인 이름
 * phoneNumber : 수령인 휴대폰 010-0000-0000
 * secondContactNumber : 두번째 연락처 00(또는 000)-000(또는 0000)-0000
 * recipientAddress : 배송지
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MyDeliveryAddress extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_address_id")
    private Long id;
    private Boolean isMain;
    private String alias;
    private String recipientName;
    private String phoneNumber;
    private String secondContactNumber;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address recipientAddress;

    // == Mapping ==
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public MyDeliveryAddress(Boolean isMain, String alias, String recipientName, String phoneNumber, String secondContactNumber) {
        this.isMain = isMain;
        this.alias = alias;
        this.recipientName = recipientName;
        this.phoneNumber = phoneNumberFormatMatching(phoneNumber);
        this.secondContactNumber = secondContactNumberFormatMatching(secondContactNumber);
    }

    public MyDeliveryAddress linkToRecipientAddress(Address address) {
        this.recipientAddress = address;
        return this;
    }

    public MyDeliveryAddress linkToUser(User user) {
        this.user = user;
        user.linkToDeliveryAddress(this);
        return this;
    }

    public MyDeliveryAddress updateFields(String alias, String recipientName, String phoneNumber, String secondContactNumber, Address address) {
        this.alias = alias;
        this.recipientName = recipientName;
        this.phoneNumber = phoneNumberFormatMatching(phoneNumber);
        this.secondContactNumber = secondContactNumberFormatMatching(secondContactNumber);
        this.recipientAddress.updateAddress(address.getCity(), address.getStreet(), address.getDetail(), address.getPostalCode());
        return this;
    }

    public MyDeliveryAddress setMain() {
        this.isMain = true;
        return this;

    }

    public MyDeliveryAddress setNotMain() {
        this.isMain = false;
        return this;

    }

    private String phoneNumberFormatMatching(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("^010(-[\\d]{4}){2}$")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        return phoneNumber;
    }

    private String secondContactNumberFormatMatching(String secondContactNumber) {
        if (secondContactNumber != null && !secondContactNumber.matches("^(010|02|031|032|033|041|042|043|044|051|052|053|054|055|061|062|063|064)-[\\d]{3,4}-[\\d]{4}$")) {
            throw new IllegalArgumentException("Invalid contact number");
        }
        return secondContactNumber;
    }
}