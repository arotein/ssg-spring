package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.global.enumeration.SalesSite;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PdtBoardThumbResDto {
    private SalesSite salesSite;
    private String brand;
    private String title;
    private Integer price;
    private Float totalScore;
    private Integer totalReviewQty;
    // 할인률

}
