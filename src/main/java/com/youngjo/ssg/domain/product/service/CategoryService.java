package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.domain.CategoryL3;
import com.youngjo.ssg.domain.product.domain.CategoryL4;
import com.youngjo.ssg.domain.product.domain.common.Category;
import com.youngjo.ssg.domain.product.dto.request.AddCtgL1ImgUrlReqDto;
import com.youngjo.ssg.domain.product.dto.request.AddCtgL1toL4ReqDto;

import java.util.List;

public interface CategoryService {
    Boolean addCtgL1toL4(AddCtgL1toL4ReqDto addCtgL1ToL4ReqDto);

    List<CategoryL1> getCtgL1All();

    List<CategoryL2> getCtgL2AllByL3Id(Long id);

    List<CategoryL3> getCtgL3AllByL4Id(Long id);

    List<CategoryL2> getCtgL2AllById(Long id);

    List<CategoryL3> getCtgL3AllById(Long id);

    List<CategoryL4> getCtgL4AllById(Long id);

    <T extends Category> List<T> getCtgDetailMenu(String ctg, Long id);

    void addCtgL1Url(AddCtgL1ImgUrlReqDto addCtgL1ImgUrlReqDto);
}
