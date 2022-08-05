package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.HappyLoungeItem;

import java.util.List;

public interface ProductService {
    List<HappyLoungeItem> getHappyLoungeItems(Integer qty);
}
