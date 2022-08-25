package com.youngjo.ssg.domain.product.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
 *
 * page는 service단에서 처리하기
 * offset = queryDto.getLimit() * (queryDto.getPage() - 1)
 */

@Getter
@Setter
@NoArgsConstructor
public class BoardSortFilterReqDto {
    @Positive
    private Integer page;
    @Positive
    private Integer limit;
    // == sort 조건 ==
    private String sort;
    @PositiveOrZero
    private Long minPrice;
    @Positive
    private Long maxPrice;

    public BoardSortFilterReqDto setDefault() {
        page = page == null ? 1 : page;
        limit = limit == null ? 40 : limit;
        sort = sort == null ? "추천순" : sort;
        return this;
    }
}
