package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.domain.DeliveryAddress;
import com.youngjo.ssg.domain.user.dto.request.AddDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.request.DeliveryAddressIdReqDto;
import com.youngjo.ssg.domain.user.dto.request.UpdateDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.response.DeliveryAddressResDto;
import com.youngjo.ssg.domain.user.repository.DeliveryAddressRepository;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private final ClientInfoLoader clientInfoLoader;
    private final UserRepository userRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    @Override
    public void addDeliveryAddress(AddDeliveryAddressReqDto deliveryAddress) {
        deliveryAddressRepository.saveDeliveryAddress(
                DeliveryAddress.builder()
                        .alias(deliveryAddress.getAlias())
                        .recipientName(deliveryAddress.getRecipientName())
                        .phoneNumber(deliveryAddress.getPhoneNumber())
                        .secondContactNumber(deliveryAddress.getSecondContactNumber())
                        .isMain(false)
                        .build()
                        .linkToRecipientAddress(deliveryAddress.getRecipientAddress())
                        .linkToUser(userRepository.findUserById(clientInfoLoader.getUserId())));
    }

    @Transactional(readOnly = true)
    @Override
    public List<DeliveryAddressResDto> getDeliveryAddress() {
        return deliveryAddressRepository.findAllDeliveryAddress(clientInfoLoader.getUserId()).stream()
                .map(DeliveryAddressResDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void updateDeliveryAddress(UpdateDeliveryAddressReqDto deliveryAddressDto) {
        deliveryAddressRepository.findDeliveryAddressById(clientInfoLoader.getUserId(), deliveryAddressDto.getDeliveryAddrId())
                .updateFields(deliveryAddressDto.getAlias(),
                        deliveryAddressDto.getRecipientName(),
                        deliveryAddressDto.getPhoneNumber(),
                        deliveryAddressDto.getSecondContactNumber(),
                        deliveryAddressDto.getRecipientAddress());
    }

    @Override
    public void updateMainDeliveryAddress(DeliveryAddressIdReqDto deliveryAddressDto) {
        DeliveryAddress mainDA = deliveryAddressRepository.findMainDeliveryAddressById(clientInfoLoader.getUserId());

        if (mainDA.getId() != deliveryAddressDto.getDeliveryAddrId()) {
            mainDA.setNotMain();
            deliveryAddressRepository.findDeliveryAddressById(clientInfoLoader.getUserId(), deliveryAddressDto.getDeliveryAddrId()).setMain();
        }
    }

    @Override
    public void delDeliveryAddress(DeliveryAddressIdReqDto deliveryAddressDto) {
        deliveryAddressRepository.removeDeliveryAddressById(clientInfoLoader.getUserId(), deliveryAddressDto.getDeliveryAddrId());
    }
}
