package com.youngjo.ssg.domain.search.controller;

import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;
import com.youngjo.ssg.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Slf4j
public class SearchController {
    private final SearchService searchService;

    @GetMapping("")
    public void search(@RequestParam String query, @Validated BoardSortFilterReqDto queryDto) {
        searchService.findSearchResult(query, queryDto);
    }
}
