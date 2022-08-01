package com.youngjo.ssg.domain.product.domain;

import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_id")
    private Long id;
    private String name;
    private String originPath;

    //==매핑==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public ProductImg(String name, String originPath) {
        this.name = name;
        this.originPath = originPath;
    }
}
