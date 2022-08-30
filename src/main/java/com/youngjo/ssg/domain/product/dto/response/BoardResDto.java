package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.ProductBoard;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardResDto {
    private Integer listIndex;
    private Long boardId;
    private String mainImgUrl;
    private String mainImgAlt;
    private String title;
    private String brand;
    private String salesSite;
    //    private Integer discountRate; -> 나중에 구현
    // == Auto Count ==
    private Integer totalReviewQty;
    private Float totalScore;
    private Boolean isSamePrice;
    private Long minPrice;
    // == ShippingInfo ==
    private Integer shippingFee;
    private Boolean boardLike;
    private Boolean isPremium;
    private Boolean isCrossBorderShipping;
    private Boolean isOnlineOnly;

    public BoardResDto(ProductBoard productBoard, Boolean boardLike) {
        this.boardId = productBoard.getId();
        this.mainImgUrl = productBoard.getMainImgPath();
        this.mainImgAlt = productBoard.getMainImgAlt();
        this.title = productBoard.getTitle();
        this.brand = productBoard.getBrand();
        this.salesSite = productBoard.getSalesSite().getValue();
        this.totalReviewQty = productBoard.getTotalReviewQty();
        this.totalScore = productBoard.getTotalScore() / (Float) 10F;
        this.isSamePrice = productBoard.getIsSamePrice();
        this.minPrice = productBoard.getMinPrice();
        this.shippingFee = productBoard.getShippingFee();
        this.isPremium = productBoard.getIsPremium();
        this.isCrossBorderShipping = productBoard.getIsCrossBorderShipping();
        this.isOnlineOnly = productBoard.getIsOnlineOnly();
        this.boardLike = boardLike != null ? boardLike : false;
    }
}
