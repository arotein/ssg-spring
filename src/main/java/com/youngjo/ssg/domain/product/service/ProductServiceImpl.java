package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.HappyLoungeItem;
import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductBoard getBoard() {
        return productRepository.findBoard();
    }

    public List<HappyLoungeItem> getHappyLoungeItems(Integer qty) {
        return productRepository.findHappyLoungeItems(qty);
    }
}
