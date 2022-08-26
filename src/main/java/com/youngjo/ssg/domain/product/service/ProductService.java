package com.youngjo.ssg.domain.product.service;

import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;
import com.youngjo.ssg.domain.product.dto.request.AddPdtBoardReqDto;
import com.youngjo.ssg.domain.product.dto.request.PdtOption1ReqDto;
import com.youngjo.ssg.domain.product.dto.response.BoardListResDto;
import com.youngjo.ssg.domain.product.dto.response.PdtBoardDetailResDto;
import com.youngjo.ssg.domain.product.dto.response.PdtOption2ResDto;

import java.util.List;

public interface ProductService {
    // == service code ==
    Boolean addPdtBoard(AddPdtBoardReqDto addPdtBoardReqDto);

    PdtBoardDetailResDto getBoardById(Long id);

    List<PdtOption2ResDto> getPdtOption2List(PdtOption1ReqDto reqDto);

    BoardListResDto getBoardListByL2Id(Long id, BoardSortFilterReqDto queryDto);

    BoardListResDto getBoardListByL3Id(Long id, BoardSortFilterReqDto queryDto);

    BoardListResDto getBoardListByL4Id(Long id, BoardSortFilterReqDto queryDto);

    Boolean pressBoardLike(Long id);
}
