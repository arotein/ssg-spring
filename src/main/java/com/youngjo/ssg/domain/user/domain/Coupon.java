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
    private Integer qty; // 수량
    private String info; //쿠폰 설명
    private Timestamp availableDate; // 사용가능 날짜
    private Timestamp expDate; // 만료날짜


    private String productCode; // 적용가능 제품 고유번호
    // private String category; // 적용가능 카테고리
    private Boolean isPresented; // 선물한 쿠폰인지
    // 선물받은사람 User와 연결하기

    //==매핑==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Coupon(String name, Integer qty, String info, Timestamp availableDate, Timestamp expDate, String productCode, Boolean isPresented, User user) {
        this.name = name;
        this.qty = qty;
        this.info = info;
        this.availableDate = availableDate;
        this.expDate = expDate;
        this.productCode = productCode;
        this.isPresented = isPresented;
        this.user = user;
    }
}
