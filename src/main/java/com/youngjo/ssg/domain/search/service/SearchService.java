package com.youngjo.ssg.domain.search.service;

import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;
import com.youngjo.ssg.domain.product.dto.response.BoardListResDto;

public interface SearchService {
    BoardListResDto findSearchResult(String query, BoardSortFilterReqDto queryDto);
}
