package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CtgL1ToL2ResDto {
    private Integer listIndex;
    private PdtStaticDto.CtgL1Dto category;
    private List<PdtStaticDto.CtgL2Dto> subCategoryList = new ArrayList<>();

    public CtgL1ToL2ResDto(CategoryL1 ctgL1, List<CategoryL2> ctgL2List) {
        this.category = new PdtStaticDto.CtgL1Dto(ctgL1.getId(), ctgL1.getName());
        this.subCategoryList = ctgL2List.stream()
                .map(ctg -> new PdtStaticDto.CtgL2Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
        this.subCategoryList.forEach(e -> e.setListIndex(this.subCategoryList.indexOf(e)));
    }
}
