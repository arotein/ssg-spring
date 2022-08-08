package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.domain.CategoryL3;
import com.youngjo.ssg.domain.product.domain.CategoryL4;
import com.youngjo.ssg.domain.product.dto.request.CtgL1toL4Init;
import com.youngjo.ssg.domain.product.repository.CategoryL1Repository;
import com.youngjo.ssg.domain.product.repository.CategoryL2Repository;
import com.youngjo.ssg.domain.product.repository.CategoryL3Repository;
import com.youngjo.ssg.domain.product.repository.CategoryL4Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryL1Repository categoryL1Repository;
    private final CategoryL2Repository categoryL2Repository;
    private final CategoryL3Repository categoryL3Repository;
    private final CategoryL4Repository categoryL4Repository;

    @Override
    public Boolean addCtgL1toL4(CtgL1toL4Init ctgL1ToL4Init) {
        CategoryL1 ctgL1 = categoryL1Repository.findByName(ctgL1ToL4Init.getCtgL1());
        CategoryL2 ctgL2 = categoryL2Repository.findByName(ctgL1ToL4Init.getCtgL2());
        if (ctgL1 == null) {
            ctgL1 = CategoryL1.builder().name(ctgL1ToL4Init.getCtgL1()).build();
        }
        if (ctgL2 == null) {
            ctgL2 = CategoryL2.builder().name(ctgL1ToL4Init.getCtgL2()).build();
            ctgL2.linkToCategoryL1(ctgL1);
        }
        CategoryL3 ctgL3 = categoryL3Repository.findByNameRelatedCtgL2(ctgL1ToL4Init.getCtgL2(), ctgL1ToL4Init.getCtgL3());
        if (ctgL3 == null) {
            ctgL3 = CategoryL3.builder().name(ctgL1ToL4Init.getCtgL3()).build();
            ctgL3.linkToCategoryL2(ctgL2);
        }
        if (ctgL1ToL4Init.getCtgL4().equals("Null")) {
            categoryL3Repository.save(ctgL3);
            return true;
        }
        CategoryL4 ctgL4 = categoryL4Repository.findByName(ctgL1ToL4Init.getCtgL4());
        if (ctgL4 == null) {
            ctgL4 = CategoryL4.builder().name(ctgL1ToL4Init.getCtgL4()).build();
        }
        ctgL4.linkToCategoryL3(ctgL3);
        categoryL4Repository.save(ctgL4);
        return true;
    }
}
