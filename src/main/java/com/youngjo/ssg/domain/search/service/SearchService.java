package com.youngjo.ssg.domain.search.service;

import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;

public interface SearchService {
    void findSearchResult(String query, BoardSortFilterReqDto queryDto);
}
