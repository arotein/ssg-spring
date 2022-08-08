package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import com.youngjo.ssg.global.common.BaseEntity;
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
@Table(name = "product_board")
public class ProductBoard extends BaseEntity {
    // Board Information -> pdt마다 링크, img 제공하는 board는 별도의 table로 구성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_board_id")
    private Long id;
    private String title;
    private String brand; // -> 목록 검색되게
    private SalesSite salesSite; // 판매 싸이트
    // Shipping Information
    private Boolean isEachShippingFee; // 배송비 개당 부과? true(1), false(0)
    private Integer shippingFee; // 0원(무료), 3000원 선택
    private Integer shippingFeeJeju; // 제주 추가금
    private Integer shippingFeeIsland; // 도서산간 추가금
    private Integer shippingFreeOver; // ~원 이상 무료배송, 10원 이상 or null
    // Product Detail Information
    private String pdtName;
    private String optionName1;
    private String optionName2;

    @ElementCollection
    private List<Image> thumbImgList = new ArrayList<>();

    @ElementCollection
    private List<Image> detailImgList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "productBoard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

    @ElementCollection
    private List<ProductRequiredInfo> requiredInfo = new ArrayList<>(); // 상품 필수정보
    //교환/반품주소, 위탁판매자 정보 추가하기

    // Auto Count
    private Integer totalReviewQty; // 리뷰 작성시 count
    private Integer totalScore; // 0.5 ~ 5점 입력받기, 리뷰 작성시마다 + 1
    private Integer minPrice; // 물건마다 가격이 다를 때. 아닐땐 null
    private Integer onePrice; // 가격이 1개일 때
    private Integer love; // 좋아요 -> user와 연결
    private Integer salesVol; // 판매량 -> 구매시마다 + 1

    // 쿠폰 엔티티 연결(만들기)

    // 카테고리 L3에 걸린 애들은 거르기.
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_l4_id")
    private CategoryL4 categoryL4;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "normal_cart_id")
    private NormalCart normalCart;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "buy_id")
//    private Buy buy; -> n:m 이므로 중간테이블 만들기. review도 buy에 걸기? cart는 주문(buy)와 1:1 단방향

    @Builder
    public ProductBoard(String title, String brand, SalesSite salesSite, Boolean isEachShippingFee, Integer shippingFee, Integer shippingFeeJeju, Integer shippingFeeIsland, Integer shippingFreeOver, String pdtName, String optionName1, String optionName2, List<Product> productList, List<ProductRequiredInfo> requiredInfo, Integer totalReviewQty, Integer totalScore, Integer minPrice, Integer onePrice, Integer love, Integer salesVol, CategoryL4 categoryL4, List<Image> thumbImgList, List<Image> detailImgList, NormalCart normalCart) {
        this.title = title;
        this.brand = brand;
        this.salesSite = salesSite;
        this.isEachShippingFee = isEachShippingFee;
        this.shippingFee = shippingFee;
        this.shippingFeeJeju = shippingFeeJeju;
        this.shippingFeeIsland = shippingFeeIsland;
        this.shippingFreeOver = shippingFreeOver;
        this.pdtName = pdtName;
        this.optionName1 = optionName1;
        this.optionName2 = optionName2;
        this.productList = productList;
        this.requiredInfo = requiredInfo;
        this.categoryL4 = categoryL4;
        this.normalCart = normalCart;
    }

    public ProductBoard linkToProductList(List<Product> productList) {
        this.productList = productList;
        int min = productList.get(0).getPrice();
        for (Product product : productList) {
            product.linkToProductBoard(this);
            if (min != product.getPrice()) {
                if (min > product.getPrice()) {
                    this.minPrice = product.getPrice();
                } else {
                    this.minPrice = min;
                }
                this.onePrice = null;
            } else {
                this.onePrice = product.getPrice();
                this.minPrice = null;
            }
        }
        return this;
    }
}
