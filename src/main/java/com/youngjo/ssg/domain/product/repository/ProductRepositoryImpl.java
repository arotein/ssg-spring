package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.ProductBoard;
import com.youngjo.ssg.domain.product.domain.ProductBoardLike;
import com.youngjo.ssg.global.common.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.youngjo.ssg.domain.product.domain.QCategoryL2.categoryL2;
import static com.youngjo.ssg.domain.product.domain.QCategoryL3.categoryL3;
import static com.youngjo.ssg.domain.product.domain.QCategoryL4.categoryL4;
import static com.youngjo.ssg.domain.product.domain.QProductBoard.productBoard;
import static com.youngjo.ssg.domain.product.domain.QProductBoardLike.productBoardLike;
import static com.youngjo.ssg.domain.user.domain.QUser.user;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    //     == service code ==
    @Override
    public <T extends BaseEntity> void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public ProductBoard findBoardById(Long boardId) {
        return queryFactory.selectFrom(productBoard)
                .where(productBoard.id.eq(boardId))
                .fetchOne();
    }

    // 단건조회 -> 상품 상세보기 페이지 좋아요 확인
    @Override
    public ProductBoardLike findBoardLikeByBoardIdAndUserId(Long boardId, Long userId) {
        return queryFactory.selectFrom(productBoardLike)
                .join(productBoardLike.productBoard, productBoard)
                .join(productBoardLike.user, user)
                .where(productBoard.id.eq(boardId),
                        user.id.eq(userId))
                .fetchOne();
    }

    // 리스트조회 -> 상품 목록보기 페이지 좋아요 확인 -> boardList와 합치기
    @Override
    public Map<Long, Boolean> findBoardLikeAllByBoardIdAndUserId(List<Long> boardIds, Long userId) {
        return queryFactory.from(productBoardLike)
                .join(productBoardLike.productBoard, productBoard)
                .join(productBoardLike.user, user)
                .where(productBoard.id.in(boardIds),
                        user.id.eq(userId))
                .transform(groupBy(productBoard.id).as(productBoardLike.value));

    }

    // offset(index)은 0부터 시작
    // orderBy기준은 뭘로 하는게 좋을까? love, salesVol, totalScore?
    @Override
    public List<ProductBoard> findBoardListByL2Id(Long id, Integer offset, Integer limit) {
        return queryFactory.selectFrom(productBoard)
                .join(productBoard.thumbImgList).fetchJoin()
                .join(productBoard.categoryL4, categoryL4).fetchJoin()
                .join(categoryL4.categoryL3, categoryL3).fetchJoin()
                .join(categoryL3.categoryL2, categoryL2).fetchJoin()
                .where(categoryL2.id.eq(id))
                .offset(offset)
                .limit(limit)
                .distinct()
                .fetch();
    }

    @Override
    public List<ProductBoard> findBoardListByL3Id(Long id, Integer offset, Integer limit) {
        return queryFactory.selectFrom(productBoard)
                .join(productBoard.thumbImgList).fetchJoin()
                .join(productBoard.categoryL4, categoryL4).fetchJoin()
                .join(categoryL4.categoryL3, categoryL3).fetchJoin()
                .where(categoryL3.id.eq(id))
                .offset(offset)
                .limit(limit)
                .distinct()
                .fetch();
    }

    @Override
    public List<ProductBoard> findBoardListByL4Id(Long id, Integer offset, Integer limit) {
        return queryFactory.selectFrom(productBoard)
                .join(productBoard.thumbImgList).fetchJoin()
                .join(productBoard.categoryL4, categoryL4).fetchJoin()
                .where(categoryL4.id.eq(id))
                .offset(offset)
                .limit(limit)
                .distinct()
                .fetch();
    }
}
