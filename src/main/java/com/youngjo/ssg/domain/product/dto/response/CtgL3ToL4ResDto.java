package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL3;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CtgL3ToL4ResDto {
    private Integer listIndex;
    private PdtStaticDto.CtgL3Dto category;
    private List<PdtStaticDto.CtgL4Dto> subCategoryList = new ArrayList<>();

    public CtgL3ToL4ResDto(CategoryL3 category) {
        this.category = new PdtStaticDto.CtgL3Dto(category.getId(), category.getName());
        this.subCategoryList = category.getCategoryL4List().stream()
                .map(ctg -> new PdtStaticDto.CtgL4Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
        this.subCategoryList.forEach(e -> e.setListIndex(this.subCategoryList.indexOf(e)));
    }
}
