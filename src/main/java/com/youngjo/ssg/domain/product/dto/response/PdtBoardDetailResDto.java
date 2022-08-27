package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import com.youngjo.ssg.global.common.AddressConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PdtBoardDetailResDto {
    private Long boardId;
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
    private String optionName1;
    private String optionName2;

    private String returnAddress;
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

    private PdtStaticDto.ConsignmentSellerInfoResDto consignmentSellerInfo;
    private PdtStaticDto.CtgL4Dto ctgL4;
    private Boolean boardLike;
    private List<PdtStaticDto.ProductImgResDto> thumbImgList;
    private List<PdtStaticDto.ProductImgResDto> detailImgList;
    private List<PdtStaticDto.ProductRequiredInfoResDto> productRequiredInfoList;
    private List<PdtStaticDto.OptionNameResDto> option1List = new ArrayList<>();

    public PdtBoardDetailResDto(ProductBoard productBoard) {
        this.boardId = productBoard.getId();
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
        this.optionName1 = productBoard.getOptionName1();
        this.optionName2 = productBoard.getOptionName2();
        this.returnAddress = AddressConverter.convertToString(productBoard.getReturnAddress());
        this.exchangeShippingFee = productBoard.getExchangeShippingFee();
        this.returnShippingFee = productBoard.getReturnShippingFee();
        this.premiumExchangeShippingFee = productBoard.getPremiumExchangeShippingFee();
        this.premiumReturnShippingFee = productBoard.getPremiumReturnShippingFee();
        this.totalReviewQty = productBoard.getTotalReviewQty();
        this.totalScore = productBoard.getTotalScore() / (Float) 10F;
        this.isSamePrice = productBoard.getIsSamePrice();
        this.minPrice = productBoard.getMinPrice();
        this.salesVol = productBoard.getSalesVol();
        this.deliveryDate = productBoard.getDeliveryDate();
        this.consignmentSellerInfo = new PdtStaticDto.ConsignmentSellerInfoResDto(
                productBoard.getConsignmentSellerInfo().getId(),
                productBoard.getConsignmentSellerInfo().getName(),
                AddressConverter.convertToString(productBoard.getConsignmentSellerInfo().getConsignmentSellerAddress()),
                productBoard.getConsignmentSellerInfo().getMailOrderNum());
        this.ctgL4 = new PdtStaticDto.CtgL4Dto(productBoard.getCategoryL4().getId(),
                productBoard.getCategoryL4().getName());
        this.thumbImgList = productBoard.getThumbImgList()
                .stream().map(img -> new PdtStaticDto.ProductImgResDto(
                        img.getId(),
                        img.getImgPath(),
                        img.getImgAlt()))
                .collect(Collectors.toList());
        this.detailImgList = productBoard.getDetailImgList()
                .stream().map(img -> new PdtStaticDto.ProductImgResDto(
                        img.getId(),
                        img.getImgPath(),
                        img.getImgAlt()))
                .collect(Collectors.toList());
        this.productRequiredInfoList = productBoard.getProductRequiredInfoList()
                .stream().map(info -> new PdtStaticDto.ProductRequiredInfoResDto(
                        info.getId(),
                        info.getInfoTitle(),
                        info.getInfoCnt()))
                .collect(Collectors.toList());
    }

    public PdtBoardDetailResDto boardLike(Boolean bool) {
        this.boardLike = bool;
        return this;
    }

    public PdtBoardDetailResDto addOption1Set(Set<String> optionSet) {
        Integer tmpId = 0;
        TreeSet<String> set = new TreeSet<>();
        set.addAll(optionSet);

        for (String e : set) {
            tmpId++;
            option1List.add(new PdtStaticDto.OptionNameResDto(tmpId, e));
        }
        return this;
    }
}
