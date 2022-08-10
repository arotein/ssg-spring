package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.CategoryL3;

import java.util.List;

public interface CategoryL3Repository {
    Long save(CategoryL3 categoryL3);

    CategoryL3 findByName(String name);

    CategoryL3 findByNameRelatedCtgL2(String nameL2, String nameL3);

    List<CategoryL3> getAllByL4IdSameL2(Long id);

    List<CategoryL3> getAllByIdSameL2(Long id);
}
