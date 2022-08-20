package com.youngjo.ssg.domain.search.service;

import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;
import com.youngjo.ssg.domain.product.repository.ProductRepository;
import com.youngjo.ssg.domain.search.domain.Search;
import com.youngjo.ssg.domain.search.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;
    private final ProductRepository productRepository;

    @Override
    public void findSearchResult(String query, BoardSortFilterReqDto queryDto) {
        // 검색어 저장
        Search search = searchRepository.findSearch(query);
        if (search == null) {
            searchRepository.saveSearch(Search.builder().query(query).build().plusFrequency());
        } else {
            search.plusFrequency();
        }
        // 검색결과 리턴
        productRepository.findAllBoardByQuery(query,
                queryDto.getOffset(),
                queryDto.getLimit(),
                queryDto.getSort(),
                queryDto.getMinPrice(),
                queryDto.getMaxPrice());
    }
}
