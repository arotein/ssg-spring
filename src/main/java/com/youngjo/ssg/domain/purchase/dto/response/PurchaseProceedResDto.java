package com.youngjo.ssg.domain.purchase.dto.response;

import com.youngjo.ssg.domain.product.domain.MainProduct;
import com.youngjo.ssg.domain.purchase.dto.PurchaseStaticDto;
import com.youngjo.ssg.domain.user.domain.MyDeliveryAddress;
import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.global.common.AddressConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/***
 * 주문하기 페이지(결제 전)
 * 할인, 포인트 생략
 * 제주, 도서산간 배송관련 기능 생략
 *
 *
 */
@Getter
@NoArgsConstructor
public class PurchaseProceedResDto {
    // == Delivery Address ==
    private PurchaseStaticDto.MyDeliAddrDto myDeliAddr;

    // == Buyer Info ==
    private String buyerName;
    private String buyerPhoneNumber;
    private String buyerEmail;
//    private String requestMessage; // 기본값 있으면 적용 -> 생략

    // == Product Info ==

    private List<PurchaseStaticDto.BoardResDto> pdtList = new ArrayList<>();

    public PurchaseProceedResDto(MyDeliveryAddress myDeliAddr, User user, List<MainProduct> pdtListWithBoard) {
        this.myDeliAddr = new PurchaseStaticDto.MyDeliAddrDto(
                myDeliAddr.getId(),
                myDeliAddr.getAlias(),
                myDeliAddr.getRecipientName(),
                myDeliAddr.getPhoneNumber(),
                AddressConverter.convertToString(myDeliAddr.getRecipientAddress()));
        this.buyerName = user.getName();
        this.buyerPhoneNumber = user.getPhoneNumber();
        this.buyerEmail = user.getEmail();
        pdtListWithBoard.forEach(pdt -> {
            this.pdtList.add(new PurchaseStaticDto.BoardResDto(
                    pdt.getProductBoard().getId(),
                    pdt.getProductBoard().getTitle(),
                    pdt.getProductBoard().getBrand(),
                    pdt.getProductBoard().getSalesSite().getValue(),
                    pdt.getProductBoard().getIsEachShippingFee(),
                    pdt.getProductBoard().getShippingFee(),
                    pdt.getProductBoard().getShippingFreeOver(),
                    pdt.getProductBoard().getMainImgPath(),
                    pdt.getProductBoard().getMainImgAlt(),

                    pdt.getId(),
                    pdt.getOptionValue1(),
                    pdt.getOptionValue2(),
                    pdt.getPrice()
            ));
        });
        this.pdtList.forEach(e -> e.setListIndex(this.pdtList.indexOf(e)));
    }
}
