package com.youngjo.ssg.domain.event.handler;

import com.youngjo.ssg.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/***
 * 상품 수정시 재고값이 아닌 증가량, 감소량을 받음.
 */
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class ProductStockHandler {
    private final ProductRepository productRepository;

    public void increaseAmount(Long pdtId, Integer amount) {
        productRepository.increaseMainProductStock(pdtId, amount);
    }

    public void decreaseAmount(Long pdtId, Integer amount) {
        productRepository.decreaseMainProductStock(pdtId, amount);
    }
}