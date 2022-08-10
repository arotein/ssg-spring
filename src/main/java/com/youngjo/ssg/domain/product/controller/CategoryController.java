package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.request.AddCtgL1ImgUrlReqDto;
import com.youngjo.ssg.domain.product.dto.request.AddCtgL1toL4ReqDto;
import com.youngjo.ssg.domain.product.dto.response.*;
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
    @GetMapping("/main") // 파라미터 없음, L1, L2만 리턴
    public List<CtgMainResDto> getCtgMain() {
        return categoryService.getCtgL1All().stream()
                .map(ctg -> new CtgMainResDto(ctg))
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/nav/ctgL2")
    public List<CtgMainResDto> getCtgL2DetailNav() {
        return getCtgMain();
    }

    // /api/ctg/detail/nav/ctgL3?id=1
    @GetMapping("/detail/nav/ctgL3")
    public List<CtgL2DetailNavMenuResDto> getCtgL3DetailNav(@RequestParam Long id) {
        return categoryService.getCtgL2AllByL3Id(id).stream()
                .map(ctg -> new CtgL2DetailNavMenuResDto(ctg))
                .collect(Collectors.toList());
    }

    // /api/ctg/detail/nav/ctgL4?id=1
    @GetMapping("/detail/nav/ctgL4")
    public List<CtgL3DetailNavMenuResDto> getCtgL4DetailNav(@RequestParam Long id) {
        return categoryService.getCtgL3AllByL4Id(id).stream()
                .map(ctg -> new CtgL3DetailNavMenuResDto(ctg))
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/menu/ctgL2")
    public List<CtgL2DetailNavMenuResDto> getCtgL2DetailMenu(@RequestParam Long id) {
        return categoryService.getCtgL2AllById(id).stream()
                .map(ctg -> new CtgL2DetailNavMenuResDto(ctg))
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/menu/ctgL3")
    public List<CtgL3DetailNavMenuResDto> getCtgL3DetailMenu(@RequestParam Long id) {
        return categoryService.getCtgL3AllById(id).stream()
                .map(ctg -> new CtgL3DetailNavMenuResDto(ctg))
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/menu/ctgL4")
    public List<CtgL4DetailMenuResDto> getCtgL4DetailMenu(@RequestParam Long id) {
        return categoryService.getCtgL4AllById(id).stream()
                .map(ctg -> new CtgL4DetailMenuResDto(ctg))
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

//    @PostMapping("/add/L2-url") -> 만들기
//    public void addCtgL2Url(@RequestBody ) {
//        categoryService.
//    }
}
