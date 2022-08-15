package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.*;
import com.youngjo.ssg.global.enumeration.SalesSite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PdtBoardDetailResDto {
    private Long id;
    private String title;
    private String brand;
    private SalesSite salesSite;

    private Boolean isEachShippingFee;
    private Boolean isPremium;
    private Boolean isCrossBorderShipping;
    private Boolean isOnlineOnly;

    private Integer shippingFee;
    private Integer shippingFreeOver;
    private Boolean availableDeliveryJeju;
    private Boolean availableDeliveryIsland;
    private Integer shippingFeeJeju;
    private Integer shippingFeeIsland;

    private String courierCompany;

    private String pdtName;

    private Address returnAddress;
    private Integer exchangeShippingFee;
    private Integer returnShippingFee;
    private Integer premiumExchangeShippingFee;
    private Integer premiumReturnShippingFee;

    private Integer totalReviewQty;
    private Float totalScore;
    private Boolean isSamePrice;
    private Long minPrice;
    private Integer salesVol;
    private Long deliveryDate;

    private ConsignmentSellerInfo consignmentSellerInfo;
    private CtgL4Dto ctgL4;
    private Boolean boardLike;
    private List<ProductImg> thumbImgList;
    private List<ProductImg> detailImgList;
    private List<MainProduct> mainProductList;
    private List<ProductRequiredInfo> productRequiredInfoList;

    public PdtBoardDetailResDto(ProductBoard productBoard) {
        this.title = productBoard.getTitle();
        this.brand = productBoard.getBrand();
        this.salesSite = productBoard.getSalesSite();
        this.isEachShippingFee = productBoard.getIsEachShippingFee();
        this.isPremium = productBoard.getIsPremium();
        this.isCrossBorderShipping = productBoard.getIsCrossBorderShipping();
        this.isOnlineOnly = productBoard.getIsOnlineOnly();
        this.shippingFee = productBoard.getShippingFee();
        this.shippingFreeOver = productBoard.getShippingFreeOver();
        this.availableDeliveryJeju = productBoard.getAvailableDeliveryJeju();
        this.availableDeliveryIsland = productBoard.getAvailableDeliveryIsland();
        this.shippingFeeJeju = productBoard.getShippingFeeJeju();
        this.shippingFeeIsland = productBoard.getShippingFeeIsland();
        this.courierCompany = productBoard.getCourierCompany();
        this.pdtName = productBoard.getPdtName();
        this.returnAddress = productBoard.getReturnAddress();
        this.exchangeShippingFee = productBoard.getExchangeShippingFee();
        this.returnShippingFee = productBoard.getReturnShippingFee();
        this.premiumExchangeShippingFee = productBoard.getPremiumExchangeShippingFee();
        this.premiumReturnShippingFee = productBoard.getPremiumReturnShippingFee();
        this.totalReviewQty = productBoard.getTotalReviewQty();
        this.totalScore = productBoard.getTotalScore();
        this.isSamePrice = productBoard.getIsSamePrice();
        this.minPrice = productBoard.getMinPrice();
        this.salesVol = productBoard.getSalesVol();
        this.deliveryDate = productBoard.getDeliveryDate();
        this.consignmentSellerInfo = productBoard.getConsignmentSellerInfo();
        this.ctgL4 = new CtgL4Dto(productBoard.getCategoryL4());
        this.thumbImgList = productBoard.getThumbImgList();
        this.detailImgList = productBoard.getDetailImgList();
        this.mainProductList = productBoard.getMainProductList();
        this.productRequiredInfoList = productBoard.getProductRequiredInfoList();
    }

    @Getter
    @AllArgsConstructor
    class CtgL4Dto {
        private Long id;
        private String name;

        public CtgL4Dto(CategoryL4 categoryL4) {
            this.id = categoryL4.getId();
            this.name = categoryL4.getName();
        }
    }
}
