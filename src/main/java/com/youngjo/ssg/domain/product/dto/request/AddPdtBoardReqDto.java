package com.youngjo.ssg.domain.product.dto.request;

import com.youngjo.ssg.domain.product.domain.*;
import com.youngjo.ssg.domain.product.dto.PdtStaticDto;
import com.youngjo.ssg.domain.user.dto.UserStaticDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
public class AddPdtBoardReqDto {
    @NotBlank
    private String title;
    @NotBlank
    private String brand;
    @NotBlank
    private String salesSite;
    private List<PdtStaticDto.TagReqDto> tagList;
    // == ShippingInfo ==
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

    // Product Detail Information
    private String pdtName;
    private String optionName1;
    private String optionName2;
    private Integer totalScore;
    private Integer totalReviewQty;

    private List<ProductRequiredInfo> requiredInfoList; // 일단은 Dto없이 엔티티 클래스로 객체생성

    // == Exchange Refund ==
    private UserStaticDto.AddressReqDto returnAddress;
    private Integer exchangeShippingFee;
    private Integer returnShippingFee;
    private Integer premiumExchangeShippingFee;
    private Integer premiumReturnShippingFee;

    private ConsignmentSellerInfo consignmentSellerInfo; // 일단은 Dto없이 엔티티 클래스로 객체생성
    // == Mapping ==
    @NotBlank
    private Long ctgL4Id;
    private List<ProductImg> thumbImgList; // 더미데이터 생성용 imgPath. 원래는 img를 저장하고 path도 백에서 생성함
    private List<ProductImg> detailImgList; // 더미데이터 생성용 imgPath. 원래는 img를 저장하고 path도 백에서 생성함
    private List<PdtStaticDto.MainProductReqDto> mainProductList;
}
