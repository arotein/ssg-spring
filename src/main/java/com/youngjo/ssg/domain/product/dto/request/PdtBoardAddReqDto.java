package com.youngjo.ssg.domain.product.dto.request;

import com.youngjo.ssg.domain.product.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class PdtBoardAddReqDto {
    private String title;
    private String brand;
    private String salesSite;
    private Boolean isEachShippingFee;
    private Integer shippingFee;
    private Integer shippingFeeJeju;
    private Integer shippingFeeIsland;
    private Integer shippingFreeOver;
    private String pdtName;
    private String pdtDetailImgUrl;
    private String optionName1;
    private String optionName2;
    private Long ctgL4Id;

    private List<Product> productList = new ArrayList<>();
}
