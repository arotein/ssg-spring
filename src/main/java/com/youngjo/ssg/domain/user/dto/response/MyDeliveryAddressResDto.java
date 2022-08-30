package com.youngjo.ssg.domain.user.dto.response;

import com.youngjo.ssg.domain.user.domain.MyDeliveryAddress;
import com.youngjo.ssg.global.common.AddressConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyDeliveryAddressResDto {
    private Integer listIndex;

    public void setListIndex(Integer listIndex) {
        this.listIndex = listIndex;
    }

    private Long id;
    private String alias;
    private String recipientName;
    private String phoneNumber;
    private String secondContactNumber;
    private String deliveryAddress;

    public MyDeliveryAddressResDto(MyDeliveryAddress myDeliveryAddress) {
        this.id = myDeliveryAddress.getId();
        this.alias = myDeliveryAddress.getAlias();
        this.recipientName = myDeliveryAddress.getRecipientName();
        this.phoneNumber = myDeliveryAddress.getPhoneNumber();
        this.secondContactNumber = myDeliveryAddress.getSecondContactNumber();
        this.deliveryAddress = AddressConverter.convertToString(myDeliveryAddress.getRecipientAddress());
    }
}
