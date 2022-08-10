package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL4;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CtgL4DetailMenuResDto {
    private CategoryL4 ctgL4;

    public CtgL4DetailMenuResDto(CategoryL4 ctgL4) {
        this.ctgL4 = ctgL4;
    }
}
