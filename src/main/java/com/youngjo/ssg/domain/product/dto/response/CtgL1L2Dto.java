package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.Category;
import com.youngjo.ssg.domain.product.domain.CategoryM;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CtgL1L2Dto {
    private Category ctgL1;
    private List<CategoryM> ctgL2List;

    @Builder
    public CtgL1L2Dto(Category ctgL1, List<CategoryM> ctgL2List) {
        this.ctgL1 = ctgL1;
        this.ctgL2List = ctgL2List;
    }
}
