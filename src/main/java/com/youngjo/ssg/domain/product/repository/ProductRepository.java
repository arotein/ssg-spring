package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.domain.ProductBoardLike;
import com.youngjo.ssg.global.common.BaseEntity;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
    // == service code ==
    <T extends BaseEntity> void save(T entity);

    ProductBoard findBoardById(Long id);

    ProductBoardLike findBoardLikeByBoardIdAndUserId(Long boardId, Long userId);

    Map<Long, Boolean> findBoardLikeMapByBoardIdAndUserId(List<Long> boardIds, Long userId);

    List<ProductBoard> findBoardListByL3Id(Long id, Integer offset, Integer limit);

    List<ProductBoard> findBoardListByL2Id(Long id, Integer offset, Integer limit);

    List<ProductBoard> findBoardListByL4Id(Long id, Integer offset, Integer limit);
}
