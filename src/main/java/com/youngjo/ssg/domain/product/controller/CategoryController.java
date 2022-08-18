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
    @GetMapping("/main")
    public List<CtgMainResDto> getCtgMain() {
        log.info("/api/ctg/main request");
        return categoryService.getCtgL1All().stream()
                .map(ctg -> new CtgMainResDto(ctg))
                .collect(Collectors.toList());
    }

    // L2 전체보기 -> L2 목록(id, name, imgUrl)
    @GetMapping("/all/ctgL2/{ctgL2Id}")
    public List<CtgL2AllImg> getAllCtgL2ByIdWithImg(@PathVariable Long ctgL2Id) {
        log.info("/api/ctg/all/ctgL2/{ctgL2Id} request");
        return categoryService.getCtgL2AllById(ctgL2Id).stream()
                .map(ctg -> new CtgL2AllImg(ctg))
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/nav/ctgL2")
    public List<CtgL1ToL2ResDto> getCtgL2DetailNav() {
        log.info("/api/ctg/detail/nav/ctgL2 request");
        return getCtgMain().stream()
                .map(ctg -> new CtgL1ToL2ResDto(ctg.getCtgL1(), ctg.getCtgL2List()))
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/nav/ctgL3/{ctgL3Id}")
    public List<CtgL2ToL3ResDto> getCtgL3DetailNav(@PathVariable Long ctgL3Id) {
        log.info("/api/ctg/detail/nav/ctgL3/{ctgL3Id} request");
        return categoryService.getCtgL2AllByL3Id(ctgL3Id).stream()
                .map(ctg -> new CtgL2ToL3ResDto(ctg))
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/nav/ctgL4/{ctgL4Id}")
    public List<CtgL3ToL4ResDto> getCtgL4DetailNav(@PathVariable Long ctgL4Id) {
        log.info("/api/ctg/detail/nav/ctgL4/{ctgL4Id} request");
        return categoryService.getCtgL3AllByL4Id(ctgL4Id).stream()
                .map(ctg -> new CtgL3ToL4ResDto(ctg))
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/menu/ctgL2/{ctgL2Id}")
    public List<CtgL2ToL3ResDto> getCtgL2DetailMenu(@PathVariable Long ctgL2Id) {
        log.info("/api/ctg/detail/menu/ctgL2/{ctgL2Id} request");
        return categoryService.getCtgL2AllById(ctgL2Id).stream()
                .map(ctg -> new CtgL2ToL3ResDto(ctg))
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/menu/ctgL3/{ctgL3Id}")
    public List<CtgL3ToL4ResDto> getCtgL3DetailMenu(@PathVariable Long ctgL3Id) {
        log.info("/api/ctg/detail/menu/ctgL3/{ctgL3Id} request");
        return categoryService.getCtgL3AllById(ctgL3Id).stream()
                .map(ctg -> new CtgL3ToL4ResDto(ctg))
                .collect(Collectors.toList());
    }

    @GetMapping("/detail/menu/ctgL4/{ctgL4Id}")
    public List<CtgL4ResDto> getCtgL4DetailMenu(@PathVariable Long ctgL4Id) {
        log.info("/api/ctg/detail/menu/ctgL4/{ctgL4Id} request");
        return categoryService.getCtgL4AllById(ctgL4Id).stream()
                .map(ctg -> new CtgL4ResDto(ctg))
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
