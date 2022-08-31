package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL4;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CtgL4ResDto {
    private Integer listIndex;
    private PdtStaticDto.CtgL4Dto category;

    public CtgL4ResDto(CategoryL4 category) {
        this.category = new PdtStaticDto.CtgL4Dto(category.getId(), category.getName());
    }
}
