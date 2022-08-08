package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.request.CtgL1toL4Init;
import com.youngjo.ssg.domain.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/ctg")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // 카테고리 목록페이지

    // category init URL
    @PostMapping("/add/L1-to-L4")
    public Boolean ctgL1Init(@RequestBody CtgL1toL4Init ctgL1ToL4Init) {
        return categoryService.addCtgL1toL4(ctgL1ToL4Init);
    }
}
