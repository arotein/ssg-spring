package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CtgMainResDto {
    private Integer listIndex;
    private CategoryL1 category;
    private List<PdtStaticDto.CtgL2Dto> subCategoryList = new ArrayList<>();

    public CtgMainResDto(CategoryL1 category) {
        this.category = category;
        this.subCategoryList = category.getCategoryL2List().stream()
                .map(ctg -> new PdtStaticDto.CtgL2Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
        this.subCategoryList.forEach(e -> e.setListIndex(this.subCategoryList.indexOf(e)));
    }
}
