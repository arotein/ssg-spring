package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.common.IdGenTable;
import com.youngjo.ssg.global.common.JsonHandler;
import com.youngjo.ssg.global.common.SeqTable;
import com.youngjo.ssg.global.enumeration.SalesSite;
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
public class ProductBoard extends BaseEntity {
    // Board Information -> pdt마다 링크, img 제공하는 board는 별도의 table로 구성
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "product_board_id")
    private Long id;
    private String title;
    private String brand; // -> 목록 검색되게
    private SalesSite salesSite = SalesSite.SSG_MALL;
    // Shipping Information
    private Boolean isEach = false; // 개당 부과? true(1), false(0)
    private Integer shippingFeeJeju = 3000; // 제주 추가금
    private Integer shippingFeeIsland = 3000; // 도서산간 추가금
    private Integer shippingFee = 3000; // 0원(무료), 3000원 선택
    private Integer shippingFreeOver = 0; // ~원 이상 무료, 10원 이상.
    // Product Detail Information
    private String pdtName;
    private String pdtModelCode;
    private String pdtDetailImgUrl;
    // singleOptions, doubleOptions 둘 중 하나만 사용
    @ElementCollection
    private List<String> singleOptions = new ArrayList<>();
    @ElementCollection
    private List<String> doubleOptions = new ArrayList<>();
    @Column(columnDefinition = "json")
    @JsonRawValue
    private String requiredInfo = "{}"; // 상품 필수정보
    // 교환/반품주소, 위탁판매자 정보 추가하기

    // Auto Count
    private Integer totalReviewQty = 0; // 리뷰 작성시 count
    private Integer totalScore = 0; // 0.5 ~ 5점 입력받기, 리뷰 작성시마다 + 1
    private Integer minPrice = 0; // product 추가시마다 + 1
    private Integer love = 0; // 좋아요 -> user와 연결
    private Integer salesVol = 0; // 판매량 -> 구매시마다 + 1

    // 쿠폰 엔티티 연결(만들기)

    // etc.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_4_id")
    private CategoryL4 categoryL4;

    @JsonIgnore
    @OneToMany(mappedBy = "productBoard", fetch = FetchType.LAZY)
    private List<ProductImg> productImgList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "normal_cart_id")
    private NormalCart normalCart;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "buy_id")
//    private Buy buy; -> n:m 이므로 중간테이블 만들기. cart는 주문(buy)와 1:1 단방향

    @Builder
    public ProductBoard(String title, String brand, String pdtName, String pdtModelCode, String pdtDetailImgUrl) {
        this.title = title;
        this.brand = brand;
        this.pdtName = pdtName;
        this.pdtModelCode = pdtModelCode;
        this.pdtDetailImgUrl = pdtDetailImgUrl;
    }

    public ProductBoard addRequiredInfo(String title, String content) {
        requiredInfo = JsonHandler.putStrData(requiredInfo, title, content);
        return this;
    }

    public ProductBoard addSingleOption(String option, Integer stock, Integer price) {
        singleOptions.add(JsonHandler.createListData(option, stock, price));
        return this;
    }

    public ProductBoard addDoubleOption(String option1, String option2, Integer stock, Integer price) {
        doubleOptions.add(JsonHandler.createListData(option1, option2, stock, price));
        return this;
    }
}
