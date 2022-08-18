package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.global.common.AddressConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PdtBoardDetailResDto {
    private Long id;
    private String title;
    private String brand;
    private String salesSite;

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

    private String returnAddress;
    private Integer exchangeShippingFee;
    private Integer returnShippingFee;
    private Integer premiumExchangeShippingFee;
    private Integer premiumReturnShippingFee;

    private Integer totalReviewQty;
    private Integer totalScore;
    private Boolean isSamePrice;
    private Long minPrice;
    private Integer salesVol;
    private Long deliveryDate;

    private ConsignmentSellerInfoDto consignmentSellerInfo;
    private CtgL4Dto ctgL4;
    private Boolean boardLike;
    private List<ProductImgDto> thumbImgList;
    private List<ProductImgDto> detailImgList;
    private List<MainProductDto> mainProductList;
    private List<ProductRequiredInfoDto> productRequiredInfoList;

    public PdtBoardDetailResDto(ProductBoard productBoard) {
        this.id = productBoard.getId();
        this.title = productBoard.getTitle();
        this.brand = productBoard.getBrand();
        this.salesSite = productBoard.getSalesSite().getValue();
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
        this.returnAddress = AddressConverter.convertToString(productBoard.getReturnAddress());
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
        this.consignmentSellerInfo = new ConsignmentSellerInfoDto(
                productBoard.getConsignmentSellerInfo().getName(),
                AddressConverter.convertToString(productBoard.getConsignmentSellerInfo().getConsignmentSellerAddress()),
                productBoard.getConsignmentSellerInfo().getMailOrderNum());
        this.ctgL4 = new CtgL4Dto(productBoard.getCategoryL4().getId(),
                productBoard.getCategoryL4().getName());
        this.thumbImgList = productBoard.getThumbImgList()
                .stream().map(img -> new ProductImgDto(img.getImgPath()))
                .collect(Collectors.toList());
        this.detailImgList = productBoard.getDetailImgList()
                .stream().map(img -> new ProductImgDto(img.getImgPath()))
                .collect(Collectors.toList());
        this.mainProductList = productBoard.getMainProductList()
                .stream().map(pdt -> new MainProductDto(
                        pdt.getModelCode(),
                        pdt.getOptionName1(),
                        pdt.getOptionValue1(),
                        pdt.getOptionName2(),
                        pdt.getOptionValue2(),
                        pdt.getPrice(),
                        pdt.getStock()))
                .collect(Collectors.toList());
        this.productRequiredInfoList = productBoard.getProductRequiredInfoList()
                .stream().map(info -> new ProductRequiredInfoDto(info.getInfoTitle(), info.getInfoCnt()))
                .collect(Collectors.toList());
    }

    public PdtBoardDetailResDto boardLike(Boolean bool) {
        this.boardLike = bool;
        return this;
    }

    @Getter
    @AllArgsConstructor
    class CtgL4Dto {
        private Long id;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    class ConsignmentSellerInfoDto {
        private String name;
        private String consignmentSellerAddress;
        private String mailOrderNum;
    }

    @Getter
    @AllArgsConstructor
    class ProductImgDto {
        private String imgPath;
    }

    @Getter
    @AllArgsConstructor
    class MainProductDto {
        private String modelCode;
        private String optionName1;
        private String optionValue1;
        private String optionName2;
        private String optionValue2;
        private Long price;
        private Integer stock;
    }

    @Getter
    @AllArgsConstructor
    class ProductRequiredInfoDto {
        private String infoTitle;
        private String infoCnt;
    }
}
