package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/***
 * ==상품 필수정보==
 * infoTitle : 필수정보 제목
 * infoCnt : 필수정보 내용
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductRequiredInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_required_info_id")
    private Long id;
    private String infoTitle;
    private String infoCnt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_board_id")
    private ProductBoard productBoard;

    public ProductRequiredInfo linkToProductBoard(ProductBoard productBoard) {
        this.productBoard = productBoard;
        return this;
    }
}
