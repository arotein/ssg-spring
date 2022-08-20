package com.youngjo.ssg.domain.product.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/***
 * = sort 값 =
 * 추천순 (default) -> 평점순
 * 판매순 int
 * 낮은가격 long
 * 높은가격 long
 * 신상품순 long
 * 리뷰많은순 int
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardSortFilterReqDto {
    @PositiveOrZero
    private Integer offset = 0;
    @Positive
    private Integer limit = 4;
    // == sort 조건 ==
    private String sort = "추천순";
    @PositiveOrZero
    private Long minPrice;
    @Positive
    private Long maxPrice;
}
