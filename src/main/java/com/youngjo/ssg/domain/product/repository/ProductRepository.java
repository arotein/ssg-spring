package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.ProductBoard;

public interface ProductRepository {
    // == service code ==
    Long save(ProductBoard productBoard);

    ProductBoard findBoard();
    // == dev code ==
}
