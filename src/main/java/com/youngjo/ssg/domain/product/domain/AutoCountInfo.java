package com.youngjo.ssg.domain.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class AutoCountInfo {
    private Integer totalReviewQty; // 리뷰 작성시 count
    private Integer totalScore; // 0.5 ~ 5점 입력받기, 리뷰 작성시마다 + 1
    private Integer minPrice; // 물건마다 가격이 다를 때. 아닐땐 null
    private Integer onePrice; // 가격이 1개일 때
    private Integer love; // 좋아요 -> user와 연결
    private Integer salesVol; // 판매량 -> 구매시마다 + 1

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        this.onePrice = null;
    }

    public void setOnePrice(Integer onePrice) {
        this.onePrice = onePrice;
        this.minPrice = null;
    }
}
