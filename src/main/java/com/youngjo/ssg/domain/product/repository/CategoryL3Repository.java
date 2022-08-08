package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.CategoryL3;

public interface CategoryL3Repository {
    CategoryL3 findByNameRelatedCtgL2(String nameL2, String nameL3);

    Long save(CategoryL3 categoryL3);

    CategoryL3 findById(Long id);
}
