package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.*;
import com.youngjo.ssg.global.enumeration.SalesSite;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class PdtBoardDetailResDto {
    private Long id;
    private String title;
    private String brand;
    private SalesSite salesSite;
    private ShippingInfo shippingInfo;
    private String pdtName;
    private String pdtDetailImgUrl;
    private String optionName1;
    private String optionName2;
    private List<Product> productList = new ArrayList<>();
    private List<ProductRequiredInfo> requiredInfo = new ArrayList<>();
    private Integer totalReviewQty;
    private Integer totalScore;
    private Integer minPrice;
    private Integer price;
    private Integer love;
    private Integer salesVol;
    private Long ctgL4Id;
    private List<Image> thumbImgList = new ArrayList<>();
    private List<Image> detailImgList = new ArrayList<>();

    public PdtBoardDetailResDto(ProductBoard pb) {
        this.id = pb.getId();
        this.title = pb.getTitle();
        this.brand = pb.getBrand();
        this.salesSite = pb.getSalesSite();
        this.salesSite = pb.getSalesSite();
        this.shippingInfo = pb.getShippingInfo();
        this.pdtName = pb.getPdtName();
        this.optionName1 = pb.getOptionName1();
        this.optionName2 = pb.getOptionName2();
        this.productList = pb.getProductList();
        this.requiredInfo = pb.getRequiredInfo();
        this.totalReviewQty = pb.getTotalReviewQty();
        this.totalScore = pb.getTotalScore();
        this.minPrice = pb.getMinPrice();
        this.price = pb.getOnePrice();
        this.love = pb.getLove();
        this.salesVol = pb.getSalesVol();
        this.ctgL4Id = pb.getCategoryL4().getId();
        this.thumbImgList = pb.getThumbImgList();
        this.detailImgList = pb.getDetailImgList();
    }
}
