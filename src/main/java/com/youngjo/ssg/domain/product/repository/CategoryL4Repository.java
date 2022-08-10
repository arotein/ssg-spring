package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.CategoryL4;

import java.util.List;

public interface CategoryL4Repository {
    Long save(CategoryL4 categoryL4);

    CategoryL4 findById(Long id);

    CategoryL4 findByName(String name);

    List<CategoryL4> getAll();

    List<CategoryL4> getAllByIdSameL3(Long id);
}
