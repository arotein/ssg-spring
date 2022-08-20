package com.youngjo.ssg.domain.user.dto.response;

import com.youngjo.ssg.domain.user.domain.DeliveryAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryAddressResDto {
    private Long id;
    private String alias;
    private String recipientName;
    private String phoneNumber;
    private String secondContactNumber;
    private AddressDto deliveryAddress;

    public DeliveryAddressResDto(DeliveryAddress deliveryAddress) {
        this.id = deliveryAddress.getId();
        this.alias = deliveryAddress.getAlias();
        this.recipientName = deliveryAddress.getRecipientName();
        this.phoneNumber = deliveryAddress.getPhoneNumber();
        this.secondContactNumber = deliveryAddress.getSecondContactNumber();
        this.deliveryAddress = new AddressDto(deliveryAddress.getRecipientAddress().getCity(),
                deliveryAddress.getRecipientAddress().getStreet(),
                deliveryAddress.getRecipientAddress().getDetail(),
                deliveryAddress.getRecipientAddress().getPostalCode());
    }

    @Getter
    @AllArgsConstructor
    class AddressDto {
        private String city;
        private String street;
        private String detail;
        private String postalCode;
    }
}
