package com.youngjo.ssg.domain.product.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CtgL1toL4Init {
    @NotEmpty
    private String ctgL1;
    @NotEmpty
    private String ctgL2;
    @NotEmpty
    private String ctgL3;
    private String ctgL4;
}