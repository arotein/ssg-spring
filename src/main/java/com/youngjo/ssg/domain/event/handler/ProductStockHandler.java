package com.youngjo.ssg.domain.event.handler;

import com.youngjo.ssg.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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