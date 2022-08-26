package com.youngjo.ssg.domain.product.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardListResDto {
    private Long totalBoardQty; // 각 카테고리가 가지고있는 전체 값임. 현재 filter를 통한 검색결과의 값은 반영안됨.
    private List<BoardResDto> boardList;

    public BoardListResDto(Long totalBoardQty, List<BoardResDto> boardList) {
        this.totalBoardQty = totalBoardQty;
        this.boardList = boardList;
    }
}
