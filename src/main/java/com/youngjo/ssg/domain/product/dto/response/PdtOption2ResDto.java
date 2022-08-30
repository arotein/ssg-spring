package com.youngjo.ssg.domain.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PdtOption2ResDto {
    private Integer listIndex;
    private Long pdtId;
    private String opt2Value;
    private Long price;
    private Integer stock;

    public PdtOption2ResDto(Long pdtId, String opt2Value, Long price, Integer stock) {
        this.pdtId = pdtId;
        this.opt2Value = opt2Value;
        this.price = price;
        this.stock = stock;
    }
}
