package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL4;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CtgL4ResDto {
    private CtgL4Dto ctgL4;

    public CtgL4ResDto(CategoryL4 ctgL4) {
        this.ctgL4 = new CtgL4Dto(ctgL4.getId(), ctgL4.getName());
    }

    @Getter
    @AllArgsConstructor
    class CtgL4Dto {
        private Long id;
        private String name;
    }
}
