package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.CategoryL4;

import java.util.List;

public interface CategoryL4Repository {
    Long save(CategoryL4 categoryL4);

    CategoryL4 findById(Long id);

    CategoryL4 findByName(String name);

    CategoryL4 findByL2L3L4Name(String ctgL2Name, String ctgL3Name, String ctgL4Name);

    List<CategoryL4> getAll();

    List<CategoryL4> getAllByIdSameL3(Long id);
}
