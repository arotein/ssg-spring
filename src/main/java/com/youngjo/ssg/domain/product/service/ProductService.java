package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.HappyLoungeItem;
import com.youngjo.ssg.domain.product.domain.ProductBoard;

import java.util.List;

public interface ProductService {
    ProductBoard getBoard();

    List<HappyLoungeItem> getHappyLoungeItems(Integer qty);
}
