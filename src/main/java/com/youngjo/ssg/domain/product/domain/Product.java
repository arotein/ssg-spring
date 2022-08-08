package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String modelCode;
    private String optionValue1;
    private String optionValue2;
    private Integer stock;
    private Integer price;

    //==매핑==
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_board_id")
    private ProductBoard productBoard;

    @Builder
    public Product(String modelCode, String optionValue1, String optionValue2, Integer stock, Integer price, ProductBoard productBoard) {
        this.modelCode = modelCode;
        this.optionValue1 = optionValue1;
        this.optionValue2 = optionValue2;
        this.stock = stock;
        this.price = price;
        this.productBoard = productBoard;
    }

    public void linkToProductBoard(ProductBoard productBoard) {
        this.productBoard = productBoard;
    }
}
