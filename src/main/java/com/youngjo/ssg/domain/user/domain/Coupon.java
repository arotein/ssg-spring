package com.youngjo.ssg.domain.user.domain;

import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String name; // 쿠폰명
    private String info; //쿠폰 설명
    private Integer qty; // 수량
    private Integer discountRate; // 할인률
    private Timestamp availableDate; // 사용가능 날짜
    private Timestamp expDate; // 만료날짜
    // private String productId; // 적용가능 제품
    // private String productCode; // 적용가능 제품 고유번호
    // private String category; // 적용가능 카테고리

    //==매핑==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @Builder
    public Coupon(String name, String info, Integer qty, Integer discountRate, Timestamp availableDate, Timestamp expDate) {
        this.name = name;
        this.info = info;
        this.qty = qty;
        this.discountRate = discountRate;
        this.availableDate = availableDate;
        this.expDate = expDate;
    }
}
