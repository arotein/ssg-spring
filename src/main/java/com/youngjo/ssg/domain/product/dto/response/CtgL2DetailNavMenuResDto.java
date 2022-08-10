package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CtgL2DetailNavMenuResDto {
    private CategoryL2 ctgL2;
    private List<CategoryL3Dto> ctgL3List = new ArrayList<>();

    public CtgL2DetailNavMenuResDto(CategoryL2 ctgL2) {
        this.ctgL2 = ctgL2;
        this.ctgL3List = ctgL2.getCategoryL3List().stream()
                .map(ctg -> new CategoryL3Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    class CategoryL3Dto {
        private Long id;
        private String name;
    }
}
