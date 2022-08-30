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
    private PdtStaticDto.CtgL2Dto ctgL2;
    private List<PdtStaticDto.CtgL3Dto> ctgL3List = new ArrayList<>();

    public CtgL2ToL3ResDto(CategoryL2 ctgL2) {
        this.ctgL2 = new PdtStaticDto.CtgL2Dto(ctgL2.getId(), ctgL2.getName());
        this.ctgL3List = ctgL2.getCategoryL3List().stream()
                .map(ctg -> new PdtStaticDto.CtgL3Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
        this.ctgL3List.forEach(e -> e.setListIndex(this.ctgL3List.indexOf(e)));
    }
}
