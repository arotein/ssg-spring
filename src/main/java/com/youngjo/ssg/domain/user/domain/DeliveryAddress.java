package com.youngjo.ssg.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.product.domain.Address;
import com.youngjo.ssg.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

/***
 * User의 배송지 관리 기능 -> 상품 주문시 필요
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DeliveryAddress extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_address_id")
    private Long id;
    private Boolean isMain; // 기본 배송지
    private String alias; // 주소 별칭
    private String recipientName; // 수령인 이름
    private String phoneNumber; // 수령인 휴대폰 010-0000-0000
    private String secondContactNumber; // 두번째 연락처 00(또는 000)-000(또는 0000)-0000
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address recipientAddress; // 배송 주소

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public DeliveryAddress(Boolean isMain, String alias, String recipientName, String phoneNumber, String secondContactNumber) {
        this.isMain = isMain;
        this.alias = alias;
        this.recipientName = recipientName;
        this.phoneNumber = phoneNumberFormatMatching(phoneNumber);
        this.secondContactNumber = secondContactNumberFormatMatching(secondContactNumber);
    }

    public DeliveryAddress linkToRecipientAddress(Address address) {
        this.recipientAddress = address;
        return this;
    }

    public DeliveryAddress linkToUser(User user) {
        this.user = user;
        user.linkToDeliveryAddress(this);
        return this;
    }

    public DeliveryAddress updateFields(String alias, String recipientName, String phoneNumber, String secondContactNumber, Address address) {
        this.alias = alias;
        this.recipientName = recipientName;
        this.phoneNumber = phoneNumberFormatMatching(phoneNumber);
        this.secondContactNumber = secondContactNumberFormatMatching(secondContactNumber);
        this.recipientAddress.updateAddress(address.getCity(), address.getStreet(), address.getDetail(), address.getPostalCode());
        return this;
    }

    public DeliveryAddress setMain() {
        this.isMain = true;
        return this;

    }

    public DeliveryAddress setNotMain() {
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