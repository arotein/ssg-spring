package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.request.AddCtgL1ImgUrlReqDto;
import com.youngjo.ssg.domain.product.dto.request.AddCtgL1toL4ReqDto;
import com.youngjo.ssg.domain.product.dto.response.*;
import com.youngjo.ssg.domain.product.service.CategoryService;
import com.youngjo.ssg.global.common.CommonResponse;
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
    @GetMapping("/main")
    public CommonResponse getCtgMain() {
        List<CtgMainResDto> list = categoryService.getCtgL1All().stream()
                .map(ctg -> new CtgMainResDto(ctg))
                .collect(Collectors.toList());
        list.forEach(e -> e.setListIndex(list.indexOf(e)));
        return CommonResponse.builder()
                .data(list)
                .build();
    }

    // L2 전체보기 -> L2 목록(id, name, imgUrl)
    @GetMapping("/all/{ctgL2Id}")
    public CommonResponse getAllCtgL2ByIdWithImg(@PathVariable Long ctgL2Id) {
        List<CtgL2AllImgResDto> list = categoryService.getCtgL2AllById(ctgL2Id).stream()
                .map(ctg -> new CtgL2AllImgResDto(ctg))
                .collect(Collectors.toList());
        list.forEach(e -> e.setListIndex(list.indexOf(e)));
        return CommonResponse.builder()
                .data(list)
                .build();
    }

    @GetMapping("/nav2")
    public CommonResponse getCtgL2DetailNav() {
        List<CtgL1ToL2ResDto> list = categoryService.getCtgL1All().stream()
                .map(ctg -> new CtgL1ToL2ResDto(ctg, ctg.getCategoryL2List()))
                .collect(Collectors.toList());
        list.forEach(e -> e.setListIndex(list.indexOf(e)));
        return CommonResponse.builder()
                .data(list)
                .build();
    }

    @GetMapping("/nav3/{ctgL3Id}")
    public CommonResponse getCtgL3DetailNav(@PathVariable Long ctgL3Id) {
        List<CtgL2ToL3ResDto> list = categoryService.getCtgL2AllByL3Id(ctgL3Id).stream()
                .map(ctg -> new CtgL2ToL3ResDto(ctg))
                .collect(Collectors.toList());
        list.forEach(e -> e.setListIndex(list.indexOf(e)));
        return CommonResponse.builder()
                .data(list)
                .build();
    }

    @GetMapping("/nav4/{ctgL4Id}")
    public CommonResponse getCtgL4DetailNav(@PathVariable Long ctgL4Id) {
        List<CtgL3ToL4ResDto> list = categoryService.getCtgL3AllByL4Id(ctgL4Id).stream()
                .map(ctg -> new CtgL3ToL4ResDto(ctg))
                .collect(Collectors.toList());
        list.forEach(e -> e.setListIndex(list.indexOf(e)));
        return CommonResponse.builder()
                .data(list)
                .build();
    }

    @GetMapping("/menu2/{ctgL2Id}")
    public CommonResponse getCtgL2DetailMenu(@PathVariable Long ctgL2Id) {
        List<CtgL2ToL3ResDto> list = categoryService.getCtgL2AllById(ctgL2Id).stream()
                .map(ctg -> new CtgL2ToL3ResDto(ctg))
                .collect(Collectors.toList());
        list.forEach(e -> e.setListIndex(list.indexOf(e)));
        return CommonResponse.builder()
                .data(list)
                .build();
    }

    @GetMapping("/menu3/{ctgL3Id}")
    public CommonResponse getCtgL3DetailMenu(@PathVariable Long ctgL3Id) {
        List<CtgL3ToL4ResDto> list = categoryService.getCtgL3AllById(ctgL3Id).stream()
                .map(ctg -> new CtgL3ToL4ResDto(ctg))
                .collect(Collectors.toList());
        list.forEach(e -> e.setListIndex(list.indexOf(e)));
        return CommonResponse.builder()
                .data(list)
                .build();
    }

    @GetMapping("/menu4/{ctgL4Id}")
    public CommonResponse getCtgL4DetailMenu(@PathVariable Long ctgL4Id) {
        List<CtgL4ResDto> list = categoryService.getCtgL4AllById(ctgL4Id).stream()
                .map(ctg -> new CtgL4ResDto(ctg))
                .collect(Collectors.toList());
        list.forEach(e -> e.setListIndex(list.indexOf(e)));

        return CommonResponse.builder()
                .data(list)
                .build();
    }

    // ==dev code==
    @PostMapping("/add/all")
    public CommonResponse addCtgAll(@RequestBody AddCtgL1toL4ReqDto dto) {
        return CommonResponse.builder()
                .data(categoryService.addCtgL1toL4(dto))
                .build();
    }

    @PostMapping("/add/L1-url")
    public CommonResponse addCtgL1Url(@RequestBody AddCtgL1ImgUrlReqDto dto) {
        categoryService.addCtgL1Url(dto);
        return CommonResponse.builder()
                .data(true)
                .build();
    }

//    @PostMapping("/add/L2-url") -> 만들기
//    public void addCtgL2Url(@RequestBody ) {
//        categoryService.
//    }
}
