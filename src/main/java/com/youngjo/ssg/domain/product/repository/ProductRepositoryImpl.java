package com.youngjo.ssg.domain.product.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.MainProduct;
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
import static com.youngjo.ssg.domain.product.domain.QConsignmentSellerInfo.consignmentSellerInfo;
import static com.youngjo.ssg.domain.product.domain.QMainProduct.mainProduct;
import static com.youngjo.ssg.domain.product.domain.QProductBoard.productBoard;
import static com.youngjo.ssg.domain.product.domain.QProductBoardLike.productBoardLike;
import static com.youngjo.ssg.domain.product.domain.QTag.tag;
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
    public Map<Long, MainProduct> findAllMainPdtByIds(List<Long> pdtIds) {
        return queryFactory.from(mainProduct)
                .where(mainProduct.id.in(pdtIds))
                .transform(groupBy(mainProduct.id).as(mainProduct));
    }

    @Override
    public Map<Long, ProductBoard> findAllBoardByPdtIds(List<Long> pdtIds) {
        return queryFactory.from(mainProduct)
                .join(mainProduct.productBoard, productBoard)
                .where(mainProduct.id.in(pdtIds))
                .distinct()
                .transform(groupBy(mainProduct.id).as(productBoard));
    }

    // board 상세보기, 좋아요
    @Override
    public ProductBoard findBoardById(Long boardId) {
        ProductBoard board = queryFactory.selectFrom(productBoard)
                .join(productBoard.returnAddress).fetchJoin()
                .join(productBoard.categoryL4).fetchJoin()
                .join(productBoard.consignmentSellerInfo, consignmentSellerInfo).fetchJoin()
                .join(consignmentSellerInfo.consignmentSellerAddress).fetchJoin()
                .where(productBoard.id.eq(boardId))
                .distinct()
                .fetchOne();
        return board;
    }

    // board 단건에 대한 좋아요 조회 -> 엔티티 그래프가 필요한게 아닌 where절에 사용할 데이터가 필요한거니 fetch join X
    // 비회원일때는 좋아요 조회할 필요가 없으니 service단에서 조건문 처리
    @Override
    public ProductBoardLike findBoardLikeByBoardIdAndUserId(Long boardId, Long userId) {
        return queryFactory.selectFrom(productBoardLike)
                .join(productBoardLike.productBoard, productBoard)
                .join(productBoardLike.user, user)
                .where(productBoard.id.eq(boardId), user.id.eq(userId))
                .distinct()
                .fetchOne();
    }

    // board 여러건에 대한 좋아요 조회
    // 비회원일때는 좋아요 조회할 필요가 없으니 service단에서 조건문 처리
    @Override
    public Map<Long, Boolean> findBoardLikeMapByBoardIdAndUserId(List<Long> boardIds, Long userId) {
        return queryFactory.from(productBoardLike)
                .join(productBoardLike.productBoard, productBoard).on(productBoard.id.in(boardIds))
                .join(productBoardLike.user, user).on(user.id.eq(userId))
                .distinct()
                .transform(groupBy(productBoard.id).as(productBoardLike.value));

    }

    // offset(index)은 0부터 시작
    @Override
    public List<ProductBoard> findBoardListByL2Id(Long id, Integer offset, Integer limit, String sort, Long minPrice, Long maxPrice) {
        return queryFactory.selectFrom(productBoard)
                .join(productBoard.categoryL4, categoryL4).fetchJoin()
                .join(categoryL4.categoryL3, categoryL3).fetchJoin()
                .join(categoryL3.categoryL2, categoryL2).fetchJoin()
                .where(categoryL2.id.eq(id),
                        goeMinPrice(minPrice),
                        loeMaxPrice(maxPrice))
                .orderBy(sortExpression(sort))
                .offset(offset)
                .limit(limit)
                .distinct()
                .fetch();
    }

    @Override
    public List<ProductBoard> findBoardListByL3Id(Long id, Integer offset, Integer limit, String sort, Long minPrice, Long maxPrice) {
        return queryFactory.selectFrom(productBoard)
                .join(productBoard.categoryL4, categoryL4).fetchJoin()
                .join(categoryL4.categoryL3, categoryL3).fetchJoin()
                .where(categoryL3.id.eq(id),
                        goeMinPrice(minPrice),
                        loeMaxPrice(maxPrice))
                .orderBy(sortExpression(sort))
                .offset(offset)
                .limit(limit)
                .distinct()
                .fetch();
    }

    @Override
    public List<ProductBoard> findBoardListByL4Id(Long id, Integer offset, Integer limit, String sort, Long minPrice, Long maxPrice) {
        return queryFactory.selectFrom(productBoard)
                .join(productBoard.categoryL4, categoryL4).fetchJoin()
                .where(categoryL4.id.eq(id),
                        goeMinPrice(minPrice),
                        loeMaxPrice(maxPrice))
                .orderBy(sortExpression(sort))
                .offset(offset)
                .limit(limit)
                .distinct()
                .fetch();
    }

    // 통합검색
    @Override
    public List<ProductBoard> findAllBoardByQuery(String query, Integer offset, Integer limit, String sort, Long minPrice, Long maxPrice) {
        return queryFactory.selectFrom(productBoard)
                .join(productBoard.tag, tag)
                .where(tag.keyword.equalsIgnoreCase(query)
                                .or(productBoard.title.containsIgnoreCase(query))
                                .or(productBoard.pdtName.containsIgnoreCase(query)),
                        goeMinPrice(minPrice),
                        loeMaxPrice(maxPrice))
                .orderBy(sortExpression(sort))
                .offset(offset)
                .limit(limit)
                .distinct()
                .fetch();
    }

    private BooleanExpression goeMinPrice(Long minPrice) {
        return minPrice == null ? null : productBoard.minPrice.goe(minPrice);
    }

    private BooleanExpression loeMaxPrice(Long maxPrice) {
        return maxPrice == null ? null : productBoard.minPrice.loe(maxPrice);
    }

    private OrderSpecifier<?> sortExpression(String sort) {
        switch (sort) {
            case "판매순":
                return productBoard.salesVol.desc();
            case "낮은가격":
                return productBoard.minPrice.asc();
            case "높은가격":
                return productBoard.minPrice.desc();
            case "신상품순":
                return productBoard.createdAt.desc();
            case "리뷰많은순":
                return productBoard.totalReviewQty.desc();
            default:
                return productBoard.totalScore.desc();
        }
    }
}
