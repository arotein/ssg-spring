package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.Category;
import com.youngjo.ssg.domain.product.dto.response.CtgL1L2Dto;

import java.util.List;

public interface CategoryService {
    List<Category> findCtgAll();

    List<CtgL1L2Dto> findAllCtgL1L2Dto();
}
