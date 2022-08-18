package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.request.PdtBoardAddReqDto;
import com.youngjo.ssg.domain.product.dto.response.BoardListResDto;
import com.youngjo.ssg.domain.product.dto.response.PdtBoardDetailResDto;
import com.youngjo.ssg.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/pdtBoard")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/detail/{boardId}")
    public PdtBoardDetailResDto getBoardDetail(@PathVariable Long boardId) {
        log.info("/api/pdtBoard/detail/{boardId} request");
        return productService.getBoardById(boardId);
    }

    @GetMapping("/list/ctgL2/{ctgL2Id}")
    public BoardListResDto getBoardListByL2Id(@PathVariable Long ctgL2Id,
                                              @RequestParam(required = false, defaultValue = "0") Integer offset,
                                              @RequestParam(required = false, defaultValue = "4") Integer limit) {
        log.info("/api/pdtBoard/list/ctgL2/{ctgL2Id} request");
        return productService.getBoardListByL2Id(ctgL2Id, offset, limit);
    }

    @GetMapping("/list/ctgL3/{ctgL3Id}")
    public BoardListResDto getBoardListByL3Id(@PathVariable Long ctgL3Id,
                                              @RequestParam(required = false, defaultValue = "0") Integer offset,
                                              @RequestParam(required = false, defaultValue = "4") Integer limit) {
        log.info("/api/pdtBoard/list/ctgL3/{ctgL3Id} request");
        return productService.getBoardListByL3Id(ctgL3Id, offset, limit);
    }

    @GetMapping("/list/ctgL4/{ctgL4Id}")
    public BoardListResDto getBoardListByL4Id(@PathVariable Long ctgL4Id,
                                              @RequestParam(required = false, defaultValue = "0") Integer offset,
                                              @RequestParam(required = false, defaultValue = "4") Integer limit) {
        log.info("/api/pdtBoard/list/ctgL4/{ctgL4Id} request");
        return productService.getBoardListByL4Id(ctgL4Id, offset, limit);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/pressLike/{boardId}")
    public Boolean pressBoardLike(@PathVariable Long boardId) {
        log.info("/api/pdtBoard/pressLike/{boardId} request");
        productService.pressBoardLike(boardId);
        return true;
    }

    // == dev code ==
    @PostMapping("/add")
    public Boolean addBoard(@RequestBody PdtBoardAddReqDto pdtBoardAddReqDto) {
        productService.addPdtBoard(pdtBoardAddReqDto);
        return true;
    }
}
