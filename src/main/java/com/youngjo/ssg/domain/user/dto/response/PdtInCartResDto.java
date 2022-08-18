package com.youngjo.ssg.domain.user.dto.response;

import com.youngjo.ssg.domain.product.domain.MainProduct;
import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import com.youngjo.ssg.global.enumeration.SalesSite;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PdtInCartResDto {
    // == Board Info ==
    private Long boardId;
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

    private String thumbImgList;

    // == Product Info ==
    private Long pdtId;
    private String optionValue1;
    private String optionValue2;
    private Long price;
    private Integer stock;

    // == Cart Info ==
    private Integer pdtQty; // 비회원일때는 null

    public PdtInCartResDto(ProductBoard board, MainProduct pdt, NormalCart cart) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.brand = board.getBrand();
        this.salesSite = board.getSalesSite();
        this.isEachShippingFee = board.getIsEachShippingFee();
        this.isPremium = board.getIsPremium();
        this.isCrossBorderShipping = board.getIsCrossBorderShipping();
        this.isOnlineOnly = board.getIsOnlineOnly();
        this.shippingFee = board.getShippingFee();
        this.shippingFreeOver = board.getShippingFreeOver();
        this.availableDeliveryJeju = board.getAvailableDeliveryJeju();
        this.availableDeliveryIsland = board.getAvailableDeliveryIsland();
        this.shippingFeeJeju = board.getShippingFeeJeju();
        this.shippingFeeIsland = board.getShippingFeeIsland();
        this.thumbImgList = board.getMainImgPath();

        this.pdtId = pdt.getId();
        this.optionValue1 = pdt.getOptionValue1();
        this.optionValue2 = pdt.getOptionValue2();
        this.price = pdt.getPrice();
        this.stock = pdt.getStock();

        this.pdtQty = cart != null ? cart.getPdtQty() : null;
    }
}
