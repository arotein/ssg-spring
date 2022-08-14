package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.ProductBoard;

import java.util.List;

public interface ProductRepository {
    // == service code ==
    Long save(ProductBoard productBoard);

    List<ProductBoard> findBoardListByL3Id(Long id, Integer offset, Integer limit);

    List<ProductBoard> findBoardListByL2Id(Long id, Integer offset, Integer limit);

    List<ProductBoard> findBoardListByL4Id(Long id, Integer offset, Integer limit);

    ProductBoard findBoardFirst(Long id);
    // == dev code ==
}
