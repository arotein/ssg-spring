package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.domain.CategoryL3;
import com.youngjo.ssg.domain.product.domain.CategoryL4;

import java.util.List;

public interface CategoryRepository {
    List<CategoryL1> findCtgL1All();

    CategoryL1 findCtgById(Long id);

    CategoryL1 findCtgL1ByName(String name);

    CategoryL2 findCtgL2ByName(String name);

    CategoryL3 findCtgL3ByName(String name);

    CategoryL4 findCtgL4ByName(String name);

    void addCtgL3(CategoryL3 categoryL3);

    void addCtgL4(CategoryL4 categoryL4);
}
