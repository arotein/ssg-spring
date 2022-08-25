package com.youngjo.ssg.domain.purchase.service;

import com.youngjo.ssg.domain.user.domain.Address;
import com.youngjo.ssg.domain.product.repository.ProductRepository;
import com.youngjo.ssg.domain.purchase.domain.UserPurchase;
import com.youngjo.ssg.domain.purchase.dto.request.PurchaseCompletedReqDto;
import com.youngjo.ssg.domain.purchase.dto.request.PurchaseProceedReqDto;
import com.youngjo.ssg.domain.purchase.dto.response.PurchaseCompletedResDto;
import com.youngjo.ssg.domain.purchase.dto.response.PurchaseProceedResDto;
import com.youngjo.ssg.domain.user.repository.MyDeliveryAddressRepository;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPurchaseServiceImpl implements UserPurchaseService {
    private final ClientInfoLoader clientInfoLoader;
    private final UserRepository userRepository;
    private final MyDeliveryAddressRepository myDeliAddrRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public PurchaseProceedResDto proceedToPayment(PurchaseProceedReqDto reqDto) {
        return new PurchaseProceedResDto(
                myDeliAddrRepository.findMyDeliveryAddressById(clientInfoLoader.getUserId(), reqDto.getMyDeliAddrId()),
                userRepository.findUserById(clientInfoLoader.getUserId()),
                productRepository.findAllMainPdtByIds(reqDto.getPdtIdList()));
    }

    @Override
    public PurchaseCompletedResDto completedToPayment(PurchaseCompletedReqDto reqDto) {
        // userPur, middle 생성
        UserPurchase.builder()
                .buyerName(reqDto.getBuyerName())
                .buyerPhoneNumber(reqDto.getBuyerPhoneNumber())
                .buyerEmail(reqDto.getBuyerEmail())
                .refundWay(reqDto.getRefundWay())
                .requestMessage(reqDto.getRequestMessage())
                .receiveWay(reqDto.getReceiveWay())
                .recipientName(reqDto.getRecipientName())
                .phoneNumber(reqDto.getPhoneNumber())
                .secondContactNumber(reqDto.getSecondContactNumber())
                .build()

                .linkToUser(userRepository.findUserById(clientInfoLoader.getUserId()))
                .linkToRecipientAddress(Address.builder()
                        .city(reqDto.getRecipientAddressReqDto().getCity())
                        .street(reqDto.getRecipientAddressReqDto().getStreet())
                        .detail(reqDto.getRecipientAddressReqDto().getDetail())
                        .postalCode(reqDto.getRecipientAddressReqDto().getPostalCode())
                        .build())
                .linkToKakaoPayment(reqDto.getKakaoPayment());
        return new PurchaseCompletedResDto();
    }
}
