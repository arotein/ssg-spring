package com.youngjo.ssg.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HappyLoungeItemDto {
    private Long id;
    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String title1;
    private String title2;
    private String text;
    private Integer price;
    private Integer like;

    @Builder
    public HappyLoungeItemDto(String imgUrl1, String imgUrl2, String imgUrl3, String imgUrl4, String title1, String title2, String text, Integer price, Integer like) {
        this.imgUrl1 = imgUrl1;
        this.imgUrl2 = imgUrl2;
        this.imgUrl3 = imgUrl3;
        this.imgUrl4 = imgUrl4;
        this.title1 = title1;
        this.title2 = title2;
        this.text = text;
        this.price = price;
        this.like = like;
    }
}
