package com.youngjo.ssg.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.product.domain.ProductBoardLike;
import com.youngjo.ssg.domain.purchase.domain.UserPurchase;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.enumeration.Grade;
import com.youngjo.ssg.global.enumeration.Role;
import com.youngjo.ssg.global.enumeration.UserStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {
    // == User Info ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    private Long point;
    private String profileImgPath;

    @Enumerated(EnumType.STRING)
    private Grade grade = Grade.FRIENDS;

    // == Mapping ==
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductBoardLike> productBoardLikeList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NormalCart> normalCartList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MyDeliveryAddress> myDeliveryAddressList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserPurchase> userPurchaseList = new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private List<Coupon> couponList = new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private List<Review> reviewList = new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<PaymentCard> paymentCardList = new ArrayList<>();

    //==시스템 정보==
    private Timestamp lastAccessTime;
    private Timestamp lastPasswordChangeTime;
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_NORMAL;
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ENABLED;

    @Builder
    public User(String loginId, String password, String name, String email, String phoneNumber) {
        this.loginId = loginIdFormatMatching(loginId);
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumberFormatMatching(phoneNumber);
    }

    public void updateLastAccessTime() {
        this.lastAccessTime = new Timestamp(System.currentTimeMillis());
    }

    public String loginIdFormatMatching(String loginId) {
        if (loginId == null || !loginId.matches("^[\\da-zA-Z]{4,16}$")) {
            throw new IllegalArgumentException("Invalid loginId");
        }
        return loginId;
    }

    public String phoneNumberFormatMatching(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("^010-\\d{3,4}-\\d{3,4}$")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        return phoneNumber;
    }

    public User linkToPdtBoardLike(ProductBoardLike productBoardLike) {
        this.productBoardLikeList.add(productBoardLike);
        return this;
    }

    public User linkToNormalCart(NormalCart normalCart) {
        this.normalCartList.add(normalCart);
        return this;
    }

    public User linkToAddress(Address address) {
        this.address = address;
        return this;
    }

    public User linkToDeliveryAddress(MyDeliveryAddress myDeliveryAddress) {
        this.myDeliveryAddressList.add(myDeliveryAddress);
        return this;
    }

    public User linkToUserPurchase(UserPurchase userPurchase) {
        this.userPurchaseList.add(userPurchase);
        return this;
    }
}
