package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.dto.request.PdtBoardAddReqDto;
import com.youngjo.ssg.domain.product.dto.response.BoardListResDto;
import com.youngjo.ssg.domain.product.dto.response.PdtBoardDetailResDto;

public interface ProductService {
    // == service code ==
    void addPdtBoard(PdtBoardAddReqDto pdtBoardAddReqDto);

    PdtBoardDetailResDto getBoardById(Long id);

    BoardListResDto getBoardListByL2Id(Long id, Integer offset, Integer limit);

    BoardListResDto getBoardListByL3Id(Long id, Integer offset, Integer limit);

    BoardListResDto getBoardListByL4Id(Long id, Integer offset, Integer limit);

    void pressBoardLike(Long id);
}
