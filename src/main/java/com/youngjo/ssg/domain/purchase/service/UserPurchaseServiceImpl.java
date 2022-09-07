package com.youngjo.ssg.domain.purchase.service;

import com.youngjo.ssg.domain.event.dto.PurchaseProcessEventDto;
import com.youngjo.ssg.domain.product.domain.MainProduct;
import com.youngjo.ssg.domain.product.repository.ProductRepository;
import com.youngjo.ssg.domain.purchase.domain.KakaoPayment;
import com.youngjo.ssg.domain.purchase.domain.PurchaseMiddleProduct;
import com.youngjo.ssg.domain.purchase.domain.UserPurchase;
import com.youngjo.ssg.domain.purchase.dto.request.PurchaseCompletedReqDto;
import com.youngjo.ssg.domain.purchase.dto.request.PurchaseProceedReqDto;
import com.youngjo.ssg.domain.purchase.dto.response.PurchaseCompletedResDto;
import com.youngjo.ssg.domain.purchase.dto.response.PurchaseProceedResDto;
import com.youngjo.ssg.domain.purchase.repository.UserPurchaseRepository;
import com.youngjo.ssg.domain.user.domain.Address;
import com.youngjo.ssg.domain.user.domain.MyDeliveryAddress;
import com.youngjo.ssg.domain.user.repository.MyDeliveryAddressRepository;
import com.youngjo.ssg.domain.user.repository.UserRepository;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPurchaseServiceImpl implements UserPurchaseService {
    private final ClientInfoLoader clientInfoLoader;
    private final UserRepository userRepository;
    private final MyDeliveryAddressRepository myDeliAddrRepository;
    private final ProductRepository productRepository;
    private final UserPurchaseRepository userPurchaseRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    @Override
    public PurchaseProceedResDto getProceedToPayment(Long pdtId) {
        return new PurchaseProceedResDto(null,
                userRepository.findUserById(clientInfoLoader.getUserId()),
                productRepository.findAllMainPdtByIds(Arrays.asList(pdtId)));
    }

    @Transactional(readOnly = true)
    @Override
    public PurchaseProceedResDto proceedToPayment(PurchaseProceedReqDto reqDto) {
        return new PurchaseProceedResDto(
                myDeliAddrRepository.findMyDeliveryAddressById(clientInfoLoader.getUserId(), reqDto.getMyDeliAddrId()),
                userRepository.findUserById(clientInfoLoader.getUserId()),
                productRepository.findAllMainPdtWithBoardByIds(reqDto.getPdtIdList()));
    }

    @Override
    public PurchaseCompletedResDto completedToPayment(PurchaseCompletedReqDto reqDto) {
        MyDeliveryAddress deliAddr = myDeliAddrRepository.findMyDeliveryAddressById(clientInfoLoader.getUserId(), reqDto.getMyDeliAddrId());

        UserPurchase userPurchase = UserPurchase.builder()
                .buyerName(reqDto.getBuyerName())
                .buyerPhoneNumber(reqDto.getBuyerPhoneNumber())
                .buyerEmail(reqDto.getBuyerEmail())
                .refundWay(reqDto.getRefundWay())
                .requestMessage(reqDto.getRequestMessage())
                .receiveWay(reqDto.getReceiveWay())
                .recipientName(deliAddr.getRecipientName())
                .phoneNumber(deliAddr.getPhoneNumber())
                .secondContactNumber(deliAddr.getSecondContactNumber())
                .build()

                .linkToUser(userRepository.findUserById(clientInfoLoader.getUserId()))
                .linkToRecipientAddress(Address.builder()
                        .city(deliAddr.getRecipientAddress().getCity())
                        .street(deliAddr.getRecipientAddress().getStreet())
                        .detail(deliAddr.getRecipientAddress().getDetail())
                        .postalCode(deliAddr.getRecipientAddress().getPostalCode())
                        .build())
                .linkToKakaoPayment(KakaoPayment.builder().build().addDataFromDto(reqDto.getKakaoPayment()))
                .returnThis();

        List<MainProduct> pdtList = productRepository.findAllMainPdtByIds(reqDto.getPdtIdMap().keySet()
                .stream().collect(Collectors.toList()));

        List<PurchaseMiddleProduct> middleList = pdtList.stream()
                .map(pdt -> PurchaseMiddleProduct.builder()
                        .pdtQty(reqDto.getPdtIdMap().get(pdt.getId()))
                        .build()
                        .linkToUserPurchase(userPurchase)
                        .linkToMainProduct(pdt))
                .collect(Collectors.toList());
        // userPurchase, middleList 저장
        userPurchaseRepository.saveUserPurchase(userPurchase);
        // 이벤트 발행
        eventPublisher.publishEvent(new PurchaseProcessEventDto(reqDto.getPdtIdMap()));

        return new PurchaseCompletedResDto(userPurchase);
    }

    @Override
    public void purchaseList() {
        // 주문조회 -> 생략
    }
}
