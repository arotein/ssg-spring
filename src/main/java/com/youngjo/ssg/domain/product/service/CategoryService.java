package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.dto.request.CtgL1toL4Init;

import java.util.List;

public interface CategoryService {
    List<CategoryL1> getCtgAll();

    Boolean addCtgL1toL4(CtgL1toL4Init ctgL1ToL4Init);
}
