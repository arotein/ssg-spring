package com.youngjo.ssg.domain.user.repository;

import com.youngjo.ssg.domain.user.domain.MyDeliveryAddress;

import java.util.List;

public interface MyDeliveryAddressRepository {
    MyDeliveryAddress saveMyDeliveryAddress(MyDeliveryAddress myDeliveryAddress);

    List<MyDeliveryAddress> findAllMyDeliveryAddress(Long userId);

    MyDeliveryAddress findMyDeliveryAddressById(Long userId, Long deliveryAddrId);

    MyDeliveryAddress findMainMyDeliveryAddressById(Long userId);

    void removeMyDeliveryAddressById(Long userId, Long deliveryAddrId);
}
