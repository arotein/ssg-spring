package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.purchase.domain.PurchaseMiddleProduct;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/***
 * stock을 제외한 모든 필드는 수정불가
 * 엔티티 삭제불가 -> 유저의 구매이력 관리때문
 * -> 판매상태(isOnSale)로 관리 -> stock = 0으로 대체
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MainProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_product_id")
    private Long id;
    private String modelCode; // 제조사에서 부여한 상품 모델명

    // 옵션이 2가지밖에 없다고 가정함
    // stock을 제외한 모든 필드는 수정불가.
    private String optionName1;
    private String optionValue1;
    private String optionName2;
    private String optionValue2;

    private Long price;
    private Integer stock; // 값 update, delete는 StockHandler를 이용. 조회는 바로 DB조회

    // == Mapping ==
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_board_id")
    private ProductBoard productBoard;

    @JsonIgnore
    @OneToMany(mappedBy = "mainProductList", fetch = FetchType.LAZY)
    private List<PurchaseMiddleProduct> purchaseMiddleProductList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "mainProduct", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NormalCart> normalCartList = new ArrayList<>();

    @Builder
    public MainProduct(String modelCode, String optionName1, String optionValue1, String optionName2, String optionValue2, Long price, Integer stock) {
        this.modelCode = modelCode;
        this.optionName1 = optionName1;
        this.optionValue1 = optionValue1;
        this.optionName2 = optionName2;
        this.optionValue2 = optionValue2;
        this.price = price;
        this.stock = stock;
    }

    public void linkToProductBoard(ProductBoard productBoard) {
        this.productBoard = productBoard;
    }

    public MainProduct linkToNormalCart(NormalCart normalCart) {
        this.normalCartList.add(normalCart);
        return this;
    }

    public MainProduct linkToPurchaseMiddleProduct(PurchaseMiddleProduct purchaseMiddleProduct) {
        this.purchaseMiddleProductList.add(purchaseMiddleProduct);
        return this;
    }

    public MainProduct increaseStock(Integer amount) {
        stock += amount;
        return this;
    }

    public MainProduct decreaseStock(Integer amount) {
        if (stock - amount <= 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        stock -= amount;
        return this;
    }

    public MainProduct notOnSale() {
        stock = 0;
        return this;
    }

    public MainProduct returnThis() {
        return this;
    }
}
