package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.CategoryL2;

import java.util.List;

public interface CategoryL2Repository {
    Long save(CategoryL2 categoryL2);

    CategoryL2 findByName(String name);

    List<CategoryL2> getAllByL3IdSameL1(Long id);

    List<CategoryL2> getAllByIdSameL1(Long id);
}
