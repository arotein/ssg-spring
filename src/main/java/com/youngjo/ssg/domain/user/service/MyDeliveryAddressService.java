package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.dto.request.AddDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.request.DeliveryAddressIdReqDto;
import com.youngjo.ssg.domain.user.dto.request.UpdateDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.response.MyDeliveryAddressResDto;

import java.util.List;

public interface MyDeliveryAddressService {
    Boolean addDeliveryAddress(AddDeliveryAddressReqDto deliveryAddress);

    MyDeliveryAddressResDto getMyMainDeliveryAddress();

    List<MyDeliveryAddressResDto> getMyDeliveryAddressList();

    Boolean updateDeliveryAddress(UpdateDeliveryAddressReqDto deliveryAddressDto);

    Boolean switchingMainDeliveryAddress(DeliveryAddressIdReqDto deliveryAddressDto);

    Boolean delDeliveryAddress(Long addrId);
}
