package com.youngjo.ssg.domain.buy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.product.domain.Product;
import com.youngjo.ssg.domain.user.domain.Delivery;
import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.common.IdGenTable;
import com.youngjo.ssg.global.common.SeqTable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdGenTable
public class Buy extends BaseEntity { //회원 주문
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "buy_id")
    private Long id;
    private String serial; // 주문 고유번호
    private Integer toalPrice;
    private String paymentType; // 결제종류
    // 결제 상세정보는 카카오 API 참고해서 작성하기

    //==매핑==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @JsonIgnore
    @OneToMany(mappedBy = "buy", fetch = FetchType.LAZY)
    private List<Product> productList;

    @Builder
    public Buy(String serial, Integer toalPrice, String paymentType) {
        this.serial = serial;
        this.toalPrice = toalPrice;
        this.paymentType = paymentType;
    }
}

/***
 * kakao 결제 API
 *
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment
 */