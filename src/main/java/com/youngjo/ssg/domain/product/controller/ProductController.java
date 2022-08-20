package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;
import com.youngjo.ssg.domain.product.dto.request.AddPdtBoardReqDto;
import com.youngjo.ssg.domain.product.dto.response.BoardListResDto;
import com.youngjo.ssg.domain.product.dto.response.PdtBoardDetailResDto;
import com.youngjo.ssg.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/pdtBoard")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/detail/{boardId}")
    public PdtBoardDetailResDto getBoardDetail(@PathVariable Long boardId) {
        return productService.getBoardById(boardId);
    }

    @GetMapping("/list/ctgL2/{ctgL2Id}")
    public BoardListResDto getBoardListByL2Id(@PathVariable Long ctgL2Id, @Validated BoardSortFilterReqDto queryDto) {
        return productService.getBoardListByL2Id(ctgL2Id, queryDto);
    }

    @GetMapping("/list/ctgL3/{ctgL3Id}")
    public BoardListResDto getBoardListByL3Id(@PathVariable Long ctgL3Id, @Validated BoardSortFilterReqDto queryDto) {
        return productService.getBoardListByL3Id(ctgL3Id, queryDto);
    }

    @GetMapping("/list/ctgL4/{ctgL4Id}")
    public BoardListResDto getBoardListByL4Id(@PathVariable Long ctgL4Id, @Validated BoardSortFilterReqDto queryDto) {
        return productService.getBoardListByL4Id(ctgL4Id, queryDto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/pressLike/{boardId}")
    public Boolean pressBoardLike(@PathVariable Long boardId) {
        productService.pressBoardLike(boardId);
        return true;
    }

    // == dev code ==
    @PostMapping("/add")
    public Boolean addBoard(@RequestBody AddPdtBoardReqDto addPdtBoardReqDto) { // 상품 키워드 10개 등록하기 -> 통합검색 조건으로 사용
        productService.addPdtBoard(addPdtBoardReqDto);
        return true;
    }
}
