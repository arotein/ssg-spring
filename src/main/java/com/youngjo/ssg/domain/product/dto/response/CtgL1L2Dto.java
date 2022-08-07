package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.domain.CategoryL2;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CtgL1L2Dto {
    private CategoryL1 ctgL1;
    private List<CategoryL2> ctgL2List;

    @Builder
    public CtgL1L2Dto(CategoryL1 ctgL1, List<CategoryL2> ctgL2List) {
        this.ctgL1 = ctgL1;
        this.ctgL2List = ctgL2List;
    }
}
