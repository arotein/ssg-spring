package com.youngjo.ssg.domain.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PdtOption2ResDto {
    private Long pdtId;
    private String opt2Value;
    private Long price;
    private Integer stock;
}
