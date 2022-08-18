package com.youngjo.ssg.domain.product.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AddCtgL1toL4ReqDto {
    @NotBlank
    private String ctgL1;
    @NotBlank
    private String ctgL2;
    @NotBlank
    private String ctgL3;
    private String ctgL4;
}