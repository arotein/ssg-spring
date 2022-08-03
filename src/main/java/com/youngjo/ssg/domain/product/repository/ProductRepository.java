package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.Category;

public interface ProductRepository {
    void addCategory(Category category);
}
