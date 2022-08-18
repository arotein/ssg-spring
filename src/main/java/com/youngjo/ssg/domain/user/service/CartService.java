package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.dto.request.PdtInCartReqDto;
import com.youngjo.ssg.domain.user.dto.response.PdtInCartResDto;

import java.util.List;

public interface CartService {
    void addProductToCart(List<PdtInCartReqDto> pdtDtoList);

    List<PdtInCartResDto> getCartPdtListByPdtIds(List<Long> pdtIds);

    List<PdtInCartResDto> getUserPdtListInCart();

    void delSoldOutPdtInUserCart();

    void delPdtInUserCart(List<Long> pdtIdList);

    void modifyPdtInUserCart(PdtInCartReqDto pdtDto);
}
