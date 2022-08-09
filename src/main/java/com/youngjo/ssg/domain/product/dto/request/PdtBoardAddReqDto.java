package com.youngjo.ssg.domain.product.dto.request;

import com.youngjo.ssg.domain.product.domain.Image;
import com.youngjo.ssg.domain.product.domain.Product;
import com.youngjo.ssg.domain.product.domain.ProductRequiredInfo;
import com.youngjo.ssg.domain.product.domain.ShippingInfo;
import com.youngjo.ssg.domain.user.domain.Address;
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

    private ShippingInfo shippingInfo;

    private String pdtName;
    private String optionName1;
    private String optionName2;

    private List<Image> thumbImgList = new ArrayList<>();
    private List<Image> detailImgList = new ArrayList<>();

    private List<ProductRequiredInfo> requiredInfo = new ArrayList<>();

    private Address exchangeRefundAddress;

    private Long ctgL4Id;

    private List<Product> productList = new ArrayList<>();
}
