package com.youngjo.ssg.domain.search.service;

import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.dto.request.BoardSortFilterReqDto;
import com.youngjo.ssg.domain.product.dto.response.BoardListResDto;
import com.youngjo.ssg.domain.product.dto.response.BoardResDto;
import com.youngjo.ssg.domain.product.repository.ProductRepository;
import com.youngjo.ssg.domain.search.domain.Search;
import com.youngjo.ssg.domain.search.repository.SearchRepository;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final ClientInfoLoader clientInfoLoader;
    private final SearchRepository searchRepository;
    private final ProductRepository productRepository;

    @Override
    public BoardListResDto findSearchResult(String query, BoardSortFilterReqDto queryDto) {
        Long userId = clientInfoLoader.getUserId();
        // 검색결과 리턴
        List<ProductBoard> boardList = productRepository.findAllBoardByQuery(
                query,
                queryDto.getLimit() * (queryDto.getPage() - 1),
                queryDto.getLimit(),
                queryDto.getSort(),
                queryDto.getMinPrice(),
                queryDto.getMaxPrice());

        // 유저의 좋아요
        Map<Long, Boolean> likeMap;
        if (boardList.size() > 0 && userId != null) {
            likeMap = productRepository.findBoardLikeMapByBoardIdAndUserId(
                    boardList.stream().map(ProductBoard::getId).collect(Collectors.toList()),
                    userId);
        } else {
            likeMap = new HashMap<>();
        }

        // 검색어 저장
        Search search = searchRepository.findSearch(query);
        if (boardList.size() > 0 && search == null) {
            searchRepository.saveSearch(Search.builder().query(query).build().plusFrequency());
        } else if (boardList.size() > 0) {
            search.plusFrequency();
        }

        // 총 검색 결과 개수
        Long boardCount = productRepository.countAllBoardByQuery(
                query,
                queryDto.getMinPrice(),
                queryDto.getMaxPrice());

        return boardList.size() > 0
                ? new BoardListResDto(
                boardCount,
                boardList.stream()
                        .map(board -> new BoardResDto(board, likeMap.getOrDefault(board.getId(), false)))
                        .collect(Collectors.toList()))
                : new BoardListResDto(0L, Arrays.asList());
    }
}
