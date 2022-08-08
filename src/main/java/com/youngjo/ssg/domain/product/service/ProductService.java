package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.dto.request.PdtBoardAddReqDto;

public interface ProductService {
    // == service code ==
    void addPdtBoard(PdtBoardAddReqDto pdtBoardAddReqDto);

    ProductBoard getBoardFirst();

    // == dev code ==
}
