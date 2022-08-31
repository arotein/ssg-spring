package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CtgL2ToL3ResDto {
    private Integer listIndex;
    private PdtStaticDto.CtgL2Dto category;
    private List<PdtStaticDto.CtgL3Dto> subCategoryList = new ArrayList<>();

    public CtgL2ToL3ResDto(CategoryL2 category) {
        this.category = new PdtStaticDto.CtgL2Dto(category.getId(), category.getName());
        this.subCategoryList = category.getCategoryL3List().stream()
                .map(ctg -> new PdtStaticDto.CtgL3Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
        this.subCategoryList.forEach(e -> e.setListIndex(this.subCategoryList.indexOf(e)));
    }
}
