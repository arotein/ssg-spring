package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.*;

import java.util.List;

public interface ProductRepository {

    ProductBoard findBoard();

    List<HappyLoungeItem> findHappyLoungeItems(Integer qty);

    void addCategory(Category category);

    void addCategoryM(CategoryM categoryM);

    void addCategoryS(CategoryS categoryS);

    void addCategorySS(CategorySS categorySS);

    Category findCategoryByName(String name);

    CategoryM findCategoryMByName(String name);

    CategoryS findCategorySByName(String name);

    CategorySS findCategorySSByName(String name);
}
