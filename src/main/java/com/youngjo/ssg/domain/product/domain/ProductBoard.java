package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.user.domain.Address;
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
    @Embedded
    private ShippingInfo shippingInfo;
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

    @Embedded
    private Address exchangeRefundAddress;
    //위탁판매자 정보 추가하기

    // Auto Count
    @Embedded
    private AutoCountInfo autoCountInfo;

    // 쿠폰 엔티티 연결(만들기)

    // 카테고리 L3에 걸린 애들은 거르기.
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_l4_id")
    private CategoryL4 categoryL4;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "normal_cart_id")
    private NormalCart normalCart; // ManyToOne가 맞나?

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "buy_id")
//    private Buy buy; -> n:m 이므로 중간테이블 만들기. review도 buy에 걸기? cart는 주문(buy)와 1:1 단방향

    @Builder
    public ProductBoard(String title, String brand, SalesSite salesSite, ShippingInfo shippingInfo, String pdtName, String optionName1, String optionName2, List<Image> thumbImgList, List<Image> detailImgList, List<ProductRequiredInfo> requiredInfo, Address exchangeRefundAddress, CategoryL4 categoryL4) {
        this.title = title;
        this.brand = brand;
        this.salesSite = salesSite;
        this.shippingInfo = shippingInfo;
        this.pdtName = pdtName;
        this.optionName1 = optionName1;
        this.optionName2 = optionName2;
        this.thumbImgList = thumbImgList;
        this.detailImgList = detailImgList;
        this.requiredInfo = requiredInfo;
        this.exchangeRefundAddress = exchangeRefundAddress;
        this.categoryL4 = categoryL4;
    }


    public ProductBoard linkToProductList(List<Product> productList) {
        this.productList = productList;
        int min = productList.get(0).getPrice();
        for (Product product : productList) {
            product.linkToProductBoard(this);
            if (min != product.getPrice()) {
                if (min > product.getPrice()) {
                    this.autoCountInfo.setMinPrice(product.getPrice());
                } else {
                    this.autoCountInfo.setMinPrice(min);
                }
            } else {
                this.autoCountInfo.setOnePrice(product.getPrice());
            }
        }
        return this;
    }
}
