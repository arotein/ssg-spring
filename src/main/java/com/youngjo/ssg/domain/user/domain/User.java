package com.youngjo.ssg.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.buy.domain.Buy;
import com.youngjo.ssg.domain.product.domain.ProductBoardLike;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.enumeration.Grade;
import com.youngjo.ssg.global.enumeration.Role;
import com.youngjo.ssg.global.enumeration.Status;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String loginId;

    private String password;
    private String name;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    //    private Address address;
    private Long point;

    @Enumerated(EnumType.STRING)
    private Grade grade = Grade.FRIENDS;

    // == Mapping ==
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductBoardLike> productBoardLikeList = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private NormalCart normalCart;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PeriodicCart periodicCart;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Coupon> couponList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Review> reviewList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PaymentCard> paymentCardList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Delivery> deliveryList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Buy> buyList = new ArrayList<>();

    //==시스템 정보==
    private Timestamp lastAccessTime;
    private Timestamp lastPasswordChangeTime;
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_NORMAL;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ENABLED;

    @Builder
    public User(String loginId, String password, String name, String email, String phone) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void updateLastAccessTime() {
        this.lastAccessTime = new Timestamp(System.currentTimeMillis());
    }

    public User linkToPdtBoardLike(ProductBoardLike productBoardLike) {
        this.productBoardLikeList.add(productBoardLike);
        return this;
    }
}
