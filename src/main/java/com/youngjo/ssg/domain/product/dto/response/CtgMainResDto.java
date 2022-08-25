package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CtgMainResDto {
    private CategoryL1 ctgL1;
    private List<PdtStaticDto.CtgL2Dto> ctgL2List = new ArrayList<>();

    public CtgMainResDto(CategoryL1 ctgL1) {
        this.ctgL1 = ctgL1;
        this.ctgL2List = ctgL1.getCategoryL2List().stream()
                .map(ctg -> new PdtStaticDto.CtgL2Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
    }
}
