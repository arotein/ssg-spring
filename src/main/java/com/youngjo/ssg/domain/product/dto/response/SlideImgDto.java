package com.youngjo.ssg.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SlideImgDto {
    Long id;
    String name;
    String url;

    @Builder
    public SlideImgDto(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
