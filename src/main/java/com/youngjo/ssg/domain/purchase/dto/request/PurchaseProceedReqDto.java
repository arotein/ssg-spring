package com.youngjo.ssg.domain.purchase.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/***
 * 사용쿠폰, 포인트 정보는 생략
 * 받는 사람 배송지는 등록된 배송지만 가능
 */
@Data
@NoArgsConstructor
public class PurchaseProceedReqDto {
    private Long myDeliAddrId = -1L;
    private List<Long> pdtIdList;
}