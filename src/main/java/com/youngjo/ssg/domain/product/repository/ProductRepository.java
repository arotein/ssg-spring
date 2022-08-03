package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.Category;
import com.youngjo.ssg.domain.product.domain.CategoryM;
import com.youngjo.ssg.domain.product.domain.CategoryS;
import com.youngjo.ssg.domain.product.domain.CategorySS;

public interface ProductRepository {
    void addCategory(Category category);

    void addCategoryM(CategoryM categoryM);

    void addCategoryS(CategoryS categoryS);

    void addCategorySS(CategorySS categorySS);

    Category findCategoryByName(String name);

    CategoryM findCategoryMByName(String name);

    CategoryS findCategorySByName(String name);

    CategorySS findCategorySSByName(String name);
}
