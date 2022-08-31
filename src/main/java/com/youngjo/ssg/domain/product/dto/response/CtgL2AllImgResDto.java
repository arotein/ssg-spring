package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CtgL2AllImgResDto {
    private Integer listIndex;
    private PdtStaticDto.CtgL2ImgResDto categoryImg;

    public CtgL2AllImgResDto(CategoryL2 categoryImg) {
        this.categoryImg = new PdtStaticDto.CtgL2ImgResDto(categoryImg.getId(), categoryImg.getName(), categoryImg.getImgUrl(), categoryImg.getImgAlt());
    }
}
