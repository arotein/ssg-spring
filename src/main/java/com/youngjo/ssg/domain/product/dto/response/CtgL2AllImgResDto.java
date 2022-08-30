package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CtgL2AllImgResDto {
    private Integer listIndex;
    private PdtStaticDto.CtgL2ImgResDto ctgL2;

    public CtgL2AllImgResDto(CategoryL2 ctgL2) {
        this.ctgL2 = new PdtStaticDto.CtgL2ImgResDto(ctgL2.getId(), ctgL2.getName(), ctgL2.getImgUrl(), ctgL2.getImgAlt());
    }
}
