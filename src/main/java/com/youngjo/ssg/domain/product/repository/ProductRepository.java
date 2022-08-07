package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.*;

import java.util.List;

public interface ProductRepository {

    ProductBoard findBoard();

    List<HappyLoungeItem> findHappyLoungeItems(Integer qty);

    void addCategory(CategoryL1 categoryL1);

    void addCategoryM(CategoryL2 categoryL2);

    void addCategoryS(CategoryL3 categoryL3);

    void addCategorySS(CategoryL4 categoryL4);
}
