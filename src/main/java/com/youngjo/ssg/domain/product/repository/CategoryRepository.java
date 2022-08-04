package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.Category;
import com.youngjo.ssg.domain.product.dto.response.CtgL1L2Dto;

import java.util.List;

public interface CategoryRepository {
    List<Category> findCtgAll();

    Category findCtgById(Long id);

    List<CtgL1L2Dto> findAllCtgL1L2Dto();
}
