package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.Category;
import com.youngjo.ssg.domain.product.dto.response.CtgL1L2Dto;
import com.youngjo.ssg.domain.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findCtgAll() {
        return categoryRepository.findCtgAll();
    }

    @Override
    public List<CtgL1L2Dto> findAllCtgL1L2Dto() {
        return categoryRepository.findAllCtgL1L2Dto();
    }
}
