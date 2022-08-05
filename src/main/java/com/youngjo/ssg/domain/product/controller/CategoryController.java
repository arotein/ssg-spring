package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.domain.Category;
import com.youngjo.ssg.domain.product.dto.response.CtgL1L2Dto;
import com.youngjo.ssg.domain.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/ctg")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // 카테고리 목록페이지
    @GetMapping("/main")
    public List<Category> categoryL1All() {
        return categoryService.getCtgAll();
    }

    @GetMapping("/main-detail")
    public List<CtgL1L2Dto> categoryL1L2All() {
        return categoryService.getAllCtgL1L2Dto();
    }

    // 카테고리 첫번째 상세페이지
    // 카테고리 두번째 상세페이지
}
