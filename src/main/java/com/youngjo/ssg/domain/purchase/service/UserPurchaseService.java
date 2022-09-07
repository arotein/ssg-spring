package com.youngjo.ssg.domain.purchase.service;

import com.youngjo.ssg.domain.purchase.dto.request.PurchaseCompletedReqDto;
import com.youngjo.ssg.domain.purchase.dto.request.PurchaseProceedReqDto;
import com.youngjo.ssg.domain.purchase.dto.response.PurchaseCompletedResDto;
import com.youngjo.ssg.domain.purchase.dto.response.PurchaseProceedResDto;

public interface UserPurchaseService {
    PurchaseProceedResDto getProceedToPayment(Long pdtId);

    PurchaseProceedResDto proceedToPayment(PurchaseProceedReqDto reqDto);

    PurchaseCompletedResDto completedToPayment(PurchaseCompletedReqDto reqDto);

    void purchaseList();
}
