package com.youngjo.ssg.domain.event.handler;

import com.youngjo.ssg.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/***
 * Propagation.MANDATORY : 부모 트랜잭션에 따름, 부모 트랜잭션 필수
 * 상품 수정시 재고값이 아닌 증가량, 감소량을 받음.
 * 상품 취소로직 -> increaseAmount -> 취소완료 후 PurchaseMiddleProduct.DeliveryStatus를 CANCELED로 설정
 */
@Component
@Transactional(propagation = Propagation.MANDATORY, timeout = 5)
@RequiredArgsConstructor
public class ProductStockHandler {
    private final ProductRepository productRepository;

    public void increaseAmount(Long pdtId, Integer amount) {
        productRepository.findMainProductById(pdtId).increaseStock(amount);
    }

    public void decreaseAmount(Long pdtId, Integer amount) {
        productRepository.findMainProductById(pdtId).decreaseStock(amount);
    }
}