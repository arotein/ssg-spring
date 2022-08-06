package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class HappyLoungeItem extends BaseEntity { // product를 올려놓은 board(추후생성)와 mapping
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "happy_lounge_item_id")
    private Long id;
    private String name;
    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String title1;
    private String title2;
    private String productTitle;
    private String brand;

    private Integer price;
    private Integer pick;
    private Boolean isFreeShipping; // 배송비 무료?
    @JsonIgnore
    private String productUrl;
    @JsonIgnore
    private String ctg;

    @Builder
    public HappyLoungeItem(String name, String imgUrl1, String imgUrl2, String imgUrl3, String imgUrl4, String title1, String title2, String productTitle, Integer price, Integer pick, Boolean isFreeShipping, String productUrl, String ctg) {
        this.name = name;
        this.imgUrl1 = imgUrl1;
        this.imgUrl2 = imgUrl2;
        this.imgUrl3 = imgUrl3;
        this.imgUrl4 = imgUrl4;
        this.title1 = title1;
        this.title2 = title2;
        this.productTitle = productTitle;
        this.price = price;
        this.pick = pick;
        this.isFreeShipping = isFreeShipping;
        this.productUrl = productUrl;
        this.ctg = ctg;
    }
}