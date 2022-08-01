package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.buy.domain.Buy;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import com.youngjo.ssg.domain.user.domain.PeriodicCart;
import com.youngjo.ssg.domain.user.domain.Review;
import com.youngjo.ssg.global.common.BaseEntity;
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
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private Integer price;
    private Integer qty;

    private Integer discountRate;
    private Timestamp availableDate; // 사용가능 날짜
    private Timestamp expDate; // 만료날짜

    //==매핑==
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_s_id")
    private CategoryS categoryS;

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
    @JoinColumn(name = "periodic_cart_id")
    private PeriodicCart periodicCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_id")
    private Buy buy;

    @Builder
    public Product(String name, Integer price, Integer qty, Integer discountRate, Timestamp availableDate, Timestamp expDate) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.discountRate = discountRate;
        this.availableDate = availableDate;
        this.expDate = expDate;
    }
}
