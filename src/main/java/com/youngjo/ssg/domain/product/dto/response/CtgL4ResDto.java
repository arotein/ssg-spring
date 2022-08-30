package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL4;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CtgL4ResDto {
    private Integer listIndex;
    private PdtStaticDto.CtgL4Dto ctgL4;

    public CtgL4ResDto(CategoryL4 ctgL4) {
        this.ctgL4 = new PdtStaticDto.CtgL4Dto(ctgL4.getId(), ctgL4.getName());
    }
}
