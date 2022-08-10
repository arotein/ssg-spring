package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL3;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CtgL3DetailNavMenuResDto {
    private CategoryL3 ctgL3;
    private List<CategoryL4Dto> ctgL4List = new ArrayList<>();

    public CtgL3DetailNavMenuResDto(CategoryL3 ctgL3) {
        this.ctgL3 = ctgL3;
        this.ctgL4List = ctgL3.getCategoryL4List().stream()
                .map(ctg -> new CategoryL4Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    class CategoryL4Dto {
        private Long id;
        private String name;
    }
}
