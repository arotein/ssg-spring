package com.youngjo.ssg.domain.product.domain;

import com.youngjo.ssg.global.common.GeneratorFileName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Embeddable
@NoArgsConstructor
public class Image {
    private String imgTitle; // 파일명 (랜덤값)
    private String imgPath; // 실제데이터는 url로 넣기

    @Builder
    public Image(String imgPath) {
        this.imgTitle = GeneratorFileName.generate();
        this.imgPath = imgPath;
    }
}
