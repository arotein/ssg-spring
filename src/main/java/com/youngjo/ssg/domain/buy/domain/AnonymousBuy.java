package com.youngjo.ssg.domain.buy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.product.domain.Product;
import com.youngjo.ssg.domain.user.domain.Address;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.common.IdGenTable;
import com.youngjo.ssg.global.common.SeqTable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdGenTable
public class AnonymousBuy extends BaseEntity { //비회원 주문
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "anonymous_buy_id")
    private Long id;
    private String serial; // 주문 고유번호
    private String recipient;
    private String phone;
    private String email;
    @Embedded
    private Address address;
    private Integer toalPrice;
    private String paymentType; // 결제종류
    // 결제 상세정보는 카카오 API 참고해서 작성하기

    //==매핑==
    @JsonIgnore
    @OneToMany(mappedBy = "buy", fetch = FetchType.LAZY)
    private List<Product> productList = new ArrayList<>();

    @Builder
    public AnonymousBuy(String serial, String recipient, String phone, String email, Address address, Integer toalPrice, String paymentType) {
        this.serial = serial;
        this.recipient = recipient;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.toalPrice = toalPrice;
        this.paymentType = paymentType;
    }
}

/***
 * kakao 결제 API
 *
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment
 */