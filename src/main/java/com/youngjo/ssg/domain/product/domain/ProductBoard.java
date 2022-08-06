package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.youngjo.ssg.domain.buy.domain.Buy;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.common.IdGenTable;
import com.youngjo.ssg.global.common.JsonHandler;
import com.youngjo.ssg.global.common.SeqTable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdGenTable
//@TypeDef(name = "json", typeClass = JSONObject.class)
public class ProductBoard extends BaseEntity {
    // board 정보
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "product_board_id")
    private Long id;
    private String name;
    private String title;
    private Integer minPrice;
    private String brand;
    private Integer love; // 좋아요
    // 배송비 정보 -> pdt마다 걸리는 board는 별도의 board table구성(상품정보에 하나의 img가 아닌 상품 각각의 img url이 걸려있음)
    private Boolean isEach; // 개당 부과? true(1), false(0)
    private Integer shippingFee; // 0원(무료), 3000원 선택
    private Integer shippingFreeOver; // ~원 이상 무료, 10원 단위
    private Integer shippingFeeJeju; // 추가금
    private Integer shippingFeeIsland; // 추가금
    // 상품 정보
    private String optionName1;
    private String optionName2;
    private String pdtDetailImgUrl;
    //    @Type(type = "json")
    @Column(columnDefinition = "json")
    @JsonRawValue
    private String requiredInfo = new JSONObject().toString(); // 상품 상세페이지
    @JsonIgnore
    @OneToMany(mappedBy = "productBoard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();
    // total 정보 -> 상품 등록시 update
    private Integer totalReviewQty;
    private Integer totalScore;


    // 상품 필수정보, 교환/반품주소, 위탁판매자 정보 추가하기

    //==그 외 매핑==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_ss_id")
    private CategorySS categorySS;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "normal_cart_id")
    private NormalCart normalCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_id")
    private Buy buy;

    @Builder
    public ProductBoard(String name, String title, Integer minPrice, String brand, Integer love, Boolean isEach, Integer shippingFee, Integer shippingFreeOver, Integer shippingFeeJeju, Integer shippingFeeIsland, String optionName1, String optionName2, String pdtDetailImgUrl) {
        this.name = name;
        this.title = title;
        this.minPrice = minPrice;
        this.brand = brand;
        this.love = love;
        this.isEach = isEach;
        this.shippingFee = shippingFee;
        this.shippingFreeOver = shippingFreeOver;
        this.shippingFeeJeju = shippingFeeJeju;
        this.shippingFeeIsland = shippingFeeIsland;
        this.optionName1 = optionName1;
        this.optionName2 = optionName2;
        this.pdtDetailImgUrl = pdtDetailImgUrl;
    }

    public ProductBoard addRequiredInfo(String key, String value) {
        requiredInfo = JsonHandler.putData(requiredInfo, key, value);
        return this;
    }

    public ProductBoard removeRequiredInfo(String key) {
        requiredInfo = JsonHandler.removeData(requiredInfo, key);
        return this;
    }
}
