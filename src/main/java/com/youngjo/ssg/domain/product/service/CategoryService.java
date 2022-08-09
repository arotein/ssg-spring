package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.domain.CategoryL3;
import com.youngjo.ssg.domain.product.dto.request.AddCtgL1ImgUrlReqDto;
import com.youngjo.ssg.domain.product.dto.request.AddCtgL1toL4ReqDto;

import java.util.List;

public interface CategoryService {
    Boolean addCtgL1toL4(AddCtgL1toL4ReqDto addCtgL1ToL4ReqDto);

    List<CategoryL1> getCtgL1ToL2();

    CategoryL3 getCtgL3ToL4ById(Long id);

    void addCtgL1Url(AddCtgL1ImgUrlReqDto addCtgL1ImgUrlReqDto);
}
