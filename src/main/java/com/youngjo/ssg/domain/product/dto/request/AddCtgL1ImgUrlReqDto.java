package com.youngjo.ssg.domain.product.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AddCtgL1ImgUrlReqDto {
    @NotEmpty
    private Long id;
    private String url;
}
