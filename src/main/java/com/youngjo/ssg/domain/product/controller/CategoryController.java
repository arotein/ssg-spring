package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.request.AddCtgL1ImgUrlReqDto;
import com.youngjo.ssg.domain.product.dto.request.AddCtgL1toL4ReqDto;
import com.youngjo.ssg.domain.product.dto.response.CtgMainResDto;
import com.youngjo.ssg.domain.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/ctg")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // 카테고리 목록페이지
    @GetMapping("/main") // L1, L2만
    public List<CtgMainResDto> getCtgMain() {
        return categoryService.getCtgL1ToL2().stream()
                .map(ctg -> new CtgMainResDto(ctg))
                .collect(Collectors.toList());
    }

    // ==dev code==
    // category init
    @PostMapping("/add/all")
    public Boolean addCtgAll(@RequestBody AddCtgL1toL4ReqDto dto) {
        return categoryService.addCtgL1toL4(dto);
    }

    @PostMapping("/add/L1-url")
    public void addCtgL1Url(@RequestBody AddCtgL1ImgUrlReqDto dto) {
        categoryService.addCtgL1Url(dto);
    }
}
