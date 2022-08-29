package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.request.AddPdtBoardReqDto;
import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;
import com.youngjo.ssg.domain.product.service.ProductService;
import com.youngjo.ssg.global.common.CommonResponse;
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
    public CommonResponse getBoardDetail(@PathVariable Long boardId) {
        return CommonResponse.builder()
                .data(productService.getBoardById(boardId))
                .build();
    }

    @GetMapping("/detail/opt2/{boardId}/{opt1Value}")
    public CommonResponse getPdtOption2List(@PathVariable Long boardId,
                                            @PathVariable String opt1Value) {
        return CommonResponse.builder()
                .data(productService.getPdtOption2List(boardId, opt1Value))
                .build();
    }

    @GetMapping("/list/ctgL1/{ctgL1Id}")
    public CommonResponse getBoardListByL1Id(@PathVariable Long ctgL1Id, @Validated BoardSortFilterReqDto sortDto) {
        return CommonResponse.builder()
                .data(productService.getBoardListByL1Id(ctgL1Id, sortDto.setDefault()))
                .build();
    }

    @GetMapping("/list/ctgL2/{ctgL2Id}")
    public CommonResponse getBoardListByL2Id(@PathVariable Long ctgL2Id, @Validated BoardSortFilterReqDto sortDto) {
        return CommonResponse.builder()
                .data(productService.getBoardListByL2Id(ctgL2Id, sortDto.setDefault()))
                .build();
    }

    @GetMapping("/list/ctgL3/{ctgL3Id}")
    public CommonResponse getBoardListByL3Id(@PathVariable Long ctgL3Id, @Validated BoardSortFilterReqDto sortDto) {
        return CommonResponse.builder()
                .data(productService.getBoardListByL3Id(ctgL3Id, sortDto.setDefault()))
                .build();
    }

    @GetMapping("/list/ctgL4/{ctgL4Id}")
    public CommonResponse getBoardListByL4Id(@PathVariable Long ctgL4Id, @Validated BoardSortFilterReqDto sortDto) {
        return CommonResponse.builder()
                .data(productService.getBoardListByL4Id(ctgL4Id, sortDto.setDefault()))
                .build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/pressLike/{boardId}")
    public CommonResponse pressBoardLike(@PathVariable Long boardId) {
        return CommonResponse.builder()
                .data(productService.pressBoardLike(boardId))
                .build();
    }

    // == dev code ==
    @PostMapping("/add")
    public CommonResponse addBoard(@RequestBody AddPdtBoardReqDto addPdtBoardReqDto) {
        return CommonResponse.builder()
                .data(productService.addPdtBoard(addPdtBoardReqDto))
                .build();
    }
}
