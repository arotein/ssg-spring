package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import com.youngjo.ssg.global.common.BaseEntity;
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
public class MainProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_product_id")
    private Long id;
    private String modelCode; // 제조사에서 부여한 상품 모델명

    // 옵션이 2가지밖에 없다고 가정함
    private String optionName1;
    private String optionValue1;
    private String optionName2;
    private String optionValue2;

    private Long price;
    private Integer stock;

    //==매핑==
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_board_id")
    private ProductBoard productBoard;

    // 카트에 담긴 제품이 삭제됨 -> 카트에는 남아있고 상품이 삭제처리되었다고 알려야되나 그냥 삭제처리함.
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
}
