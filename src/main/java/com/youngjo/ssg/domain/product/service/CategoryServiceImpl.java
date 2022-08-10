package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.domain.CategoryL3;
import com.youngjo.ssg.domain.product.domain.CategoryL4;
import com.youngjo.ssg.domain.product.domain.common.Category;
import com.youngjo.ssg.domain.product.dto.request.AddCtgL1ImgUrlReqDto;
import com.youngjo.ssg.domain.product.dto.request.AddCtgL1toL4ReqDto;
import com.youngjo.ssg.domain.product.repository.CategoryL1Repository;
import com.youngjo.ssg.domain.product.repository.CategoryL2Repository;
import com.youngjo.ssg.domain.product.repository.CategoryL3Repository;
import com.youngjo.ssg.domain.product.repository.CategoryL4Repository;
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
    private final CategoryL1Repository categoryL1Repository;
    private final CategoryL2Repository categoryL2Repository;
    private final CategoryL3Repository categoryL3Repository;
    private final CategoryL4Repository categoryL4Repository;

    @Override
    public Boolean addCtgL1toL4(AddCtgL1toL4ReqDto addCtgL1ToL4ReqDto) {
        CategoryL1 ctgL1 = categoryL1Repository.findByName(addCtgL1ToL4ReqDto.getCtgL1());
        CategoryL2 ctgL2 = categoryL2Repository.findByName(addCtgL1ToL4ReqDto.getCtgL2());
        if (ctgL1 == null) {
            ctgL1 = CategoryL1.builder().name(addCtgL1ToL4ReqDto.getCtgL1()).build();
        }
        if (ctgL2 == null) {
            ctgL2 = CategoryL2.builder().name(addCtgL1ToL4ReqDto.getCtgL2()).build();
            ctgL2.linkToCategoryL1(ctgL1);
        }
        CategoryL3 ctgL3 = categoryL3Repository.findByNameRelatedCtgL2(addCtgL1ToL4ReqDto.getCtgL2(), addCtgL1ToL4ReqDto.getCtgL3());
        if (ctgL3 == null) {
            ctgL3 = CategoryL3.builder().name(addCtgL1ToL4ReqDto.getCtgL3()).build();
            ctgL3.linkToCategoryL2(ctgL2);
            categoryL3Repository.save(ctgL3);
        }
        if (addCtgL1ToL4ReqDto.getCtgL4().equals("Null")) {
            categoryL3Repository.save(ctgL3);
            return true;
        }
        CategoryL4 ctgL4 = CategoryL4.builder().name(addCtgL1ToL4ReqDto.getCtgL4()).build();
        ctgL4.linkToCategoryL3(ctgL3);
        categoryL4Repository.save(ctgL4);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryL1> getCtgL1All() {
        return categoryL1Repository.getAll();
    }

    // nav
    @Override
    @Transactional(readOnly = true)
    public List<CategoryL2> getCtgL2AllByL3Id(Long id) {
        return categoryL2Repository.getAllByL3IdSameL1(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryL3> getCtgL3AllByL4Id(Long id) {
        return categoryL3Repository.getAllByL4IdSameL2(id);
    }

    // menu
    @Override
    @Transactional(readOnly = true)
    public List<CategoryL2> getCtgL2AllById(Long id) {
        return categoryL2Repository.getAllByIdSameL1(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryL3> getCtgL3AllById(Long id) {
        return categoryL3Repository.getAllByIdSameL2(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryL4> getCtgL4AllById(Long id) {
        return categoryL4Repository.getAllByIdSameL3(id);
    }


    @Override
    public List<? extends Category> getCtgDetailMenu(String ctg, Long id) {
        return null;
    }

    @Override
    public void addCtgL1Url(AddCtgL1ImgUrlReqDto dto) {
        categoryL1Repository.addUrl(dto.getId(), dto.getUrl());
    }
}
