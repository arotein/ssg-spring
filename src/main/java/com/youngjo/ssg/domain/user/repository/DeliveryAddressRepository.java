package com.youngjo.ssg.domain.user.repository;

import com.youngjo.ssg.domain.user.domain.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressRepository {
    DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress);

    List<DeliveryAddress> findAllDeliveryAddress(Long userId);

    DeliveryAddress findDeliveryAddressById(Long userId, Long deliveryAddrId);

    DeliveryAddress findMainDeliveryAddressById(Long userId);

    void removeDeliveryAddressById(Long userId, Long deliveryAddrId);
}
