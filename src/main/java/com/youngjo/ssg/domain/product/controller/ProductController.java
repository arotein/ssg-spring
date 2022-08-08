package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.request.PdtBoardAddReqDto;
import com.youngjo.ssg.domain.product.dto.response.PdtBoardDetailResDto;
import com.youngjo.ssg.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/pdt")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add-board")
    public Boolean addBoard(@RequestBody PdtBoardAddReqDto pdtBoardAddReqDto) {
        System.out.println("pdtBoardAddReqDto = " + pdtBoardAddReqDto);
        productService.addPdtBoard(pdtBoardAddReqDto);
        return true;
    }

    @GetMapping("/board-first")
    public PdtBoardDetailResDto getBoardFirst() {
        return new PdtBoardDetailResDto(productService.getBoardFirst());
    }
}
