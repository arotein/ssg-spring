package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.domain.CategoryL3;
import com.youngjo.ssg.domain.product.domain.CategoryL4;
import com.youngjo.ssg.domain.product.dto.request.CtgL1toL4Init;
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
    public List<CategoryL1> getCtgAll() {
        return categoryRepository.findCtgL1All();
    }

    @Override
    public Boolean addCtgL1toL4(CtgL1toL4Init ctgL1ToL4Init) {
        CategoryL1 ctg1 = categoryRepository.findCtgL1ByName(ctgL1ToL4Init.getCtgL1());
        CategoryL2 ctg2 = categoryRepository.findCtgL2ByName(ctgL1ToL4Init.getCtgL2());
        CategoryL3 ctg3 = categoryRepository.findCtgL3ByName(ctgL1ToL4Init.getCtgL3());

        if (ctg1 == null) {
            ctg1 = CategoryL1.builder().name(ctgL1ToL4Init.getCtgL1()).build();
        }
        if (ctg2 == null) {
            ctg2 = CategoryL2.builder().name(ctgL1ToL4Init.getCtgL2()).build();
        }
        if (ctg3 == null) {
            ctg3 = CategoryL3.builder().name(ctgL1ToL4Init.getCtgL3()).build();
        }
        ctg2.linkToCategoryL1(ctg1);
        ctg3.linkToCategoryL2(ctg2);
        if (ctgL1ToL4Init.getCtgL4().equals("Null")) {
            categoryRepository.addCtgL3(ctg3);
        } else {
            CategoryL4 ctg4 = CategoryL4.builder().name(ctgL1ToL4Init.getCtgL4()).build();
            ctg4.linkToCategoryL3(ctg3);
            categoryRepository.addCtgL4(ctg4);
        }
        return true;
    }
}
