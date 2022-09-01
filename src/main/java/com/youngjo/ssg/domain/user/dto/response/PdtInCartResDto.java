package com.youngjo.ssg.domain.user.dto.response;

import com.youngjo.ssg.domain.product.domain.MainProduct;
import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PdtInCartResDto {
    private Integer listIndex;

    public void setListIndex(Integer listIndex) {
        this.listIndex = listIndex;
    }

    // == Board Info ==
    private Long boardId;
    private String title;
    private String brand;
    private String salesSite;

    private Boolean isEachShippingFee;
    private Integer shippingFee;
    private Integer shippingFreeOver;
    private Boolean availableDeliveryJeju;
    private Boolean availableDeliveryIsland;
    private Integer shippingFeeJeju;
    private Integer shippingFeeIsland;

    private String thumbImg;
    private String thumbImgAlt;

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
        this.salesSite = board.getSalesSite().getValue();
        this.isEachShippingFee = board.getIsEachShippingFee();
        this.shippingFee = board.getShippingFee();
        this.shippingFreeOver = board.getShippingFreeOver();
        this.availableDeliveryJeju = board.getAvailableDeliveryJeju();
        this.availableDeliveryIsland = board.getAvailableDeliveryIsland();
        this.shippingFeeJeju = board.getShippingFeeJeju();
        this.shippingFeeIsland = board.getShippingFeeIsland();
        this.thumbImg = board.getMainImgPath();
        this.thumbImgAlt = board.getMainImgAlt();

        this.pdtId = pdt.getId();
        this.optionValue1 = pdt.getOptionValue1();
        this.optionValue2 = pdt.getOptionValue2();
        this.price = pdt.getPrice();
        this.stock = pdt.getStock();

        this.pdtQty = cart != null ? cart.getPdtQty() : null;
    }
}
