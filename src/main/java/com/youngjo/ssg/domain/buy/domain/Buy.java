package com.youngjo.ssg.domain.buy.domain;

import com.youngjo.ssg.domain.user.domain.Delivery;
import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Buy extends BaseEntity {
    // User Buy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_id")
    private Long id;
    private Integer totalPrice;
    private String paymentType; // 결제종류
    // 결제 상세정보 중간테이블 만들어서 작성하기. -> 카카오 API 참고

    //==매핑==
    // cascade 유의
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "delivery_id")
//    private Delivery delivery;

//    @JsonIgnore
//    @OneToMany(mappedBy = "buy", fetch = FetchType.LAZY)
//    private List<ProductBoard> productBoardList;

    @Builder
    public Buy(Integer totalPrice, String paymentType, User user, Delivery delivery) {
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
//        this.user = user;
//        this.delivery = delivery;
    }
}

/***
 * kakao 결제 API
 *
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment
 */