package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.dto.request.PdtBoardAddReqDto;
import com.youngjo.ssg.domain.product.dto.response.BoardListResDto;

public interface ProductService {
    // == service code ==
    void addPdtBoard(PdtBoardAddReqDto pdtBoardAddReqDto);

    BoardListResDto getBoardListByL2Id(Long id, Integer offset, Integer limit);

    BoardListResDto getBoardListByL3Id(Long id, Integer offset, Integer limit);

    BoardListResDto getBoardListByL4Id(Long id, Integer offset, Integer limit);

    ProductBoard getBoardById(Long id);

    // == dev code ==
}
