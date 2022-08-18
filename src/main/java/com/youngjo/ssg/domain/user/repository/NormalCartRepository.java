package com.youngjo.ssg.domain.user.repository;

import com.youngjo.ssg.domain.user.domain.NormalCart;

import java.util.List;

public interface NormalCartRepository {
    Long saveNormalCart(NormalCart normalCart);

    List<NormalCart> findAllNormalCartByUserId(Long userId);

    void removePdtInUserCart(List<Long> pdtIdList, Long userId);

    void removeSoldOutPdtInUserCart(Long userId);

    NormalCart findPdtInUserCartByPdtId(Long pdtId, Long userId);
}
