package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.dto.request.AddDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.request.DeliveryAddressIdReqDto;
import com.youngjo.ssg.domain.user.dto.request.UpdateDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.response.DeliveryAddressResDto;

import java.util.List;

public interface DeliveryAddressService {
    void addDeliveryAddress(AddDeliveryAddressReqDto deliveryAddress);

    List<DeliveryAddressResDto> getDeliveryAddress();

    void updateDeliveryAddress(UpdateDeliveryAddressReqDto deliveryAddressDto);

    void updateMainDeliveryAddress(DeliveryAddressIdReqDto deliveryAddressDto);

    void delDeliveryAddress(DeliveryAddressIdReqDto deliveryAddressDto);
}
