package com.youngjo.ssg.domain.purchase.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.util.List;

/***
 * 사용쿠폰, 포인트 정보는 생략
 * 받는 사람 배송지는 등록된 배송지만 가능
 */
@Getter
@NoArgsConstructor
public class PurchaseProceedReqDto {
    @Positive
    private Long myDeliAddrId;
    private List<Long> pdtIdList;
}