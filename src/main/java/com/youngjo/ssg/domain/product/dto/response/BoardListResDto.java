package com.youngjo.ssg.domain.product.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardListResDto {
    private Integer totalBoardQty;
    private List<BoardResDto> boardList;

    public BoardListResDto(Integer totalBoardQty, List<BoardResDto> boardList) {
        this.totalBoardQty = totalBoardQty;
        this.boardList = boardList;
    }
}
