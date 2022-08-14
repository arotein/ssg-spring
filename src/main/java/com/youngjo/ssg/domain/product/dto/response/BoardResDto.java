package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.global.enumeration.SalesSite;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResDto {
    private Long boardId;
    private String mainImgUrl;
    private String title;
    private String brand;
    private SalesSite salesSite;
    //    private Integer discountRate; -> 나중에 구현
    // == Auto Count ==
    private Integer totalReviewQty;
    private Float totalScore;
    private Boolean isSamePrice;
    private Integer minPrice;
    // == ShippingInfo ==
    private Integer shippingFee;
    private Boolean isPremium;
    private Boolean isCrossBorderShipping;
    private Boolean isOnlineOnly;

    public BoardResDto(ProductBoard productBoard) {
        this.boardId = productBoard.getId();
        this.mainImgUrl = productBoard.getThumbImgList().get(0).getImgPath();
        this.title = productBoard.getTitle();
        this.brand = productBoard.getBrand();
        this.salesSite = productBoard.getSalesSite();
        this.totalReviewQty = productBoard.getTotalReviewQty();
        this.totalScore = productBoard.getTotalScore();
        this.isSamePrice = productBoard.getIsSamePrice();
        this.minPrice = productBoard.getMinPrice();
        this.shippingFee = productBoard.getShippingFee();
        this.isPremium = productBoard.getIsPremium();
        this.isCrossBorderShipping = productBoard.getIsCrossBorderShipping();
        this.isOnlineOnly = productBoard.getIsOnlineOnly();
    }
}
