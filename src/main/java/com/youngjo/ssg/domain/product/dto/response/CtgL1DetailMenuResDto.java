package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CtgL1DetailMenuResDto {
    private CategoryL1 ctgL1;
    private List<CategoryL2Dto> ctgL2List = new ArrayList<>();

    public CtgL1DetailMenuResDto(CategoryL1 ctgL1) {
        this.ctgL1 = ctgL1;
        this.ctgL2List = ctgL1.getCategoryL2List().stream()
                .map(ctg -> new CategoryL2Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    class CategoryL2Dto {
        private Long id;
        private String name;
    }
}
