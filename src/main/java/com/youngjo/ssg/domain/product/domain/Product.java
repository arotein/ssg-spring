package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.buy.domain.Buy;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import com.youngjo.ssg.domain.user.domain.Review;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.common.IdGenTable;
import com.youngjo.ssg.global.common.SeqTable;
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
@IdGenTable
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private Integer price;
    //    private Integer qty; -> 옵션에 추가
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_board_id")
    private ProductBoard productBoard;
    private String option1;
    private String option2;


    //==쿠폰== -> 엔티티 만들기
    private Integer discountRate;
    private Timestamp availableDate; // 사용가능 날짜
    private Timestamp expDate; // 만료날짜
    //==쿠폰 끝==

    //==매핑==
    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductImg> productImgList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "normal_cart_id")
    private NormalCart normalCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_id")
    private Buy buy;

    @Builder
    public Product(String name, Integer price, ProductBoard productBoard, String option1, String option2, Integer discountRate, Timestamp availableDate, Timestamp expDate) {
        this.name = name;
        this.price = price;
        this.productBoard = productBoard;
        this.option1 = option1;
        this.option2 = option2;
        this.discountRate = discountRate;
        this.availableDate = availableDate;
        this.expDate = expDate;
    }
}
