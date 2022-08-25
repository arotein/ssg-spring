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
    private PdtStaticDto.CtgL3Dto ctgL3;
    private List<PdtStaticDto.CtgL4Dto> ctgL4List = new ArrayList<>();

    public CtgL3ToL4ResDto(CategoryL3 ctgL3) {
        this.ctgL3 = new PdtStaticDto.CtgL3Dto(ctgL3.getId(), ctgL3.getName());
        this.ctgL4List = ctgL3.getCategoryL4List().stream()
                .map(ctg -> new PdtStaticDto.CtgL4Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
    }
}
