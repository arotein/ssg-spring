package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.domain.Address;
import com.youngjo.ssg.domain.user.domain.MyDeliveryAddress;
import com.youngjo.ssg.domain.user.dto.request.AddDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.request.DeliveryAddressIdReqDto;
import com.youngjo.ssg.domain.user.dto.request.UpdateDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.response.MyDeliveryAddressResDto;
import com.youngjo.ssg.domain.user.repository.MyDeliveryAddressRepository;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MyDeliveryAddressServiceImpl implements MyDeliveryAddressService {
    private final ClientInfoLoader clientInfoLoader;
    private final UserRepository userRepository;
    private final MyDeliveryAddressRepository myDeliveryAddressRepository;

    @Override
    public Boolean addDeliveryAddress(AddDeliveryAddressReqDto deliveryAddress) {
        myDeliveryAddressRepository.saveMyDeliveryAddress(
                MyDeliveryAddress.builder()
                        .alias(deliveryAddress.getAlias())
                        .recipientName(deliveryAddress.getRecipientName())
                        .phoneNumber(deliveryAddress.getPhoneNumber())
                        .secondContactNumber(deliveryAddress.getSecondContactNumber())
                        .isMain(false)
                        .build()
                        .linkToRecipientAddress(Address.builder()
                                .city(deliveryAddress.getRecipientAddressReqDto().getCity())
                                .street(deliveryAddress.getRecipientAddressReqDto().getStreet())
                                .street(deliveryAddress.getRecipientAddressReqDto().getStreet())
                                .postalCode(deliveryAddress.getRecipientAddressReqDto().getPostalCode())
                                .build())
                        .linkToUser(userRepository.findUserById(clientInfoLoader.getUserId())));
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<MyDeliveryAddressResDto> getMyDeliveryAddressList() {
        return myDeliveryAddressRepository.findAllMyDeliveryAddress(clientInfoLoader.getUserId()).stream()
                .map(MyDeliveryAddressResDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean updateDeliveryAddress(UpdateDeliveryAddressReqDto deliveryAddressDto) {
        myDeliveryAddressRepository.findMyDeliveryAddressById(clientInfoLoader.getUserId(), deliveryAddressDto.getDeliveryAddrId())
                .updateFields(deliveryAddressDto.getAlias(),
                        deliveryAddressDto.getRecipientName(),
                        deliveryAddressDto.getPhoneNumber(),
                        deliveryAddressDto.getSecondContactNumber(),
                        Address.builder()
                                .city(deliveryAddressDto.getRecipientAddress().getCity())
                                .street(deliveryAddressDto.getRecipientAddress().getStreet())
                                .detail(deliveryAddressDto.getRecipientAddress().getDetail())
                                .postalCode(deliveryAddressDto.getRecipientAddress().getPostalCode())
                                .build());
        return true;
    }

    @Override
    public Boolean switchingMainDeliveryAddress(DeliveryAddressIdReqDto deliveryAddressDto) {
        MyDeliveryAddress mainDA = myDeliveryAddressRepository.findMainMyDeliveryAddressById(clientInfoLoader.getUserId());

        if (mainDA.getId() != deliveryAddressDto.getDeliveryAddrId()) {
            mainDA.setNotMain();
            myDeliveryAddressRepository.findMyDeliveryAddressById(clientInfoLoader.getUserId(), deliveryAddressDto.getDeliveryAddrId()).setMain();
        }
        return true;
    }

    @Override
    public Boolean delDeliveryAddress(DeliveryAddressIdReqDto deliveryAddressDto) {
        myDeliveryAddressRepository.removeMyDeliveryAddressById(clientInfoLoader.getUserId(), deliveryAddressDto.getDeliveryAddrId());
        return true;
    }
}
