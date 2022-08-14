package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CtgL2AllImg {
    private CtgL2Dto ctgL2;

    public CtgL2AllImg(CategoryL2 ctgL2) {
        this.ctgL2 = new CtgL2Dto(ctgL2.getId(), ctgL2.getName(), ctgL2.getImgUrl());
    }

    @Data
    @AllArgsConstructor
    class CtgL2Dto {
        private Long id;
        private String name;
        private String imgUrl;
    }
}
