package com.youngjo.ssg.domain.product.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AddCtgL1ImgUrlReqDto {
    @NotBlank
    private Long id;
    private String url;
}
