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
import static com.youngjo.ssg.domain.product.domain.QCategoryL1.categoryL1;
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
    public Map<Long, MainProduct> findAllMainPdtMapByIds(List<Long> pdtIds) {
        return queryFactory.from(mainProduct)
                .where(mainProduct.id.in(pdtIds))
                .transform(groupBy(mainProduct.id).as(mainProduct));
    }

    @Override
    public Map<Long, ProductBoard> findAllBoardMapByPdtIds(List<Long> pdtIds) {
        return queryFactory.from(mainProduct)
                .join(mainProduct.productBoard, productBoard)
                .where(mainProduct.id.in(pdtIds))
                .distinct()
                .transform(groupBy(mainProduct.id).as(productBoard));
    }

    @Override
    public List<MainProduct> findAllMainPdtWithBoardByIds(List<Long> pdtIds) {
        return queryFactory.selectFrom(mainProduct)
                .join(mainProduct.productBoard, productBoard).fetchJoin()
                .where(mainProduct.id.in(pdtIds))
                .fetch();
    }

    @Override
    public List<MainProduct> findAllMainPdtByIds(List<Long> pdtIds) {
        return queryFactory.selectFrom(mainProduct)
                .where(mainProduct.id.in(pdtIds))
                .fetch();
    }

    @Override
    public List<ProductBoard> findAllBoardByPdtIds(List<Long> pdtIds) {
        return queryFactory.select(productBoard)
                .from(mainProduct)
                .join(mainProduct.productBoard, productBoard).fetchJoin()
                .where(mainProduct.id.in(pdtIds))
                .distinct()
                .fetch();
    }

    // board ????????????, ?????????
    @Override
    public ProductBoard findBoardById(Long boardId) {
        return queryFactory.selectFrom(productBoard)
                .join(productBoard.returnAddress).fetchJoin()
                .join(productBoard.categoryL4).fetchJoin()
                .join(productBoard.consignmentSellerInfo, consignmentSellerInfo).fetchJoin()
                .join(consignmentSellerInfo.consignmentSellerAddress).fetchJoin()
                .where(productBoard.id.eq(boardId))
                .distinct()
                .fetchOne();
    }

    // board ????????? ?????? ????????? ?????? -> ????????? ???????????? ???????????? ?????? where?????? ????????? ???????????? ??????????????? fetch join X
    // ?????????????????? ????????? ????????? ????????? ????????? service????????? ????????? ??????
    // ??? board??? ?????? ??????????????? ????????? boardLike?????? ???????????? ????????? ?????? ???
    @Override
    public ProductBoardLike findBoardLikeByBoardIdAndUserId(Long boardId, Long userId) {
        return queryFactory.selectFrom(productBoardLike)
                .join(productBoardLike.productBoard, productBoard)
                .join(productBoardLike.user, user)
                .where(productBoard.id.eq(boardId),
                        eqUserId(userId))
                .distinct()
                .fetchOne();
    }

    // board ???????????? ?????? ????????? ??????
    // ?????????????????? ????????? ????????? ????????? ????????? service????????? ????????? ??????
    @Override
    public Map<Long, Boolean> findBoardLikeMapByBoardIdAndUserId(List<Long> boardIds, Long userId) {
        return queryFactory.from(productBoardLike)
                .join(productBoardLike.productBoard, productBoard).on(productBoard.id.in(boardIds))
                .join(productBoardLike.user, user).on(user.id.eq(userId))
                .distinct()
                .transform(groupBy(productBoard.id).as(productBoardLike.value));
    }

    // ????????? ?????? ????????? ?????????
    @Override
    public List<ProductBoardLike> findAllBoardLikeByUserId(Long userId, Integer offset, Integer limit) {
        return queryFactory.selectFrom(productBoardLike)
                .join(productBoardLike.productBoard).fetchJoin()
                .join(productBoardLike.user, user)
                .where(user.id.eq(userId),
                        productBoardLike.value.isTrue())
                .orderBy(productBoardLike.updatedAt.desc(),
                        productBoardLike.createdAt.desc())
                .offset(offset)
                .limit(limit)
                .distinct()
                .fetch();
    }

    @Override
    public Long countAllBoardLike(Long userId) {
        return queryFactory.select(productBoardLike.count())
                .from(productBoardLike)
                .join(productBoardLike.user, user)
                .where(user.id.eq(userId),
                        productBoardLike.value.isTrue())
                .distinct()
                .fetchOne();
    }

    // offset(index)??? 0?????? ??????
    @Override
    public List<ProductBoard> findBoardListByL1Id(Long id, Integer offset, Integer limit, String sort, Long minPrice, Long maxPrice) {
        return queryFactory.selectFrom(productBoard)
                .join(productBoard.categoryL4, categoryL4)
                .join(categoryL4.categoryL3, categoryL3)
                .join(categoryL3.categoryL2, categoryL2)
                .join(categoryL2.categoryL1, categoryL1)
                .where(categoryL1.id.eq(id),
                        goeMinPrice(minPrice),
                        loeMaxPrice(maxPrice))
                .orderBy(sortExpression(sort))
                .offset(offset)
                .limit(limit)
                .distinct()
                .fetch();
    }

    @Override
    public List<ProductBoard> findBoardListByL2Id(Long id, Integer offset, Integer limit, String sort, Long minPrice, Long maxPrice) {
        return queryFactory.selectFrom(productBoard)
                .join(productBoard.categoryL4, categoryL4)
                .join(categoryL4.categoryL3, categoryL3)
                .join(categoryL3.categoryL2, categoryL2)
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
                .join(productBoard.categoryL4, categoryL4)
                .join(categoryL4.categoryL3, categoryL3)
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
                .join(productBoard.categoryL4, categoryL4)
                .where(categoryL4.id.eq(id),
                        goeMinPrice(minPrice),
                        loeMaxPrice(maxPrice))
                .orderBy(sortExpression(sort))
                .offset(offset)
                .limit(limit)
                .distinct()
                .fetch();
    }

    // ???????????? ?????? count(*) -> ?????? ??????. ????????? ?????? ???????????? ????????? ??????
    @Override
    public Long countAllBoardByCtgId(Long ctgL1Id, Long ctgL2Id, Long ctgL3Id, Long ctgL4Id, Long minPrice, Long maxPrice) {
        return queryFactory.select(productBoard.count())
                .from(productBoard)
                .join(productBoard.categoryL4, categoryL4)
                .join(categoryL4.categoryL3, categoryL3)
                .join(categoryL3.categoryL2, categoryL2)
                .join(categoryL2.categoryL1, categoryL1)
                .where(eqCtgL1Id(ctgL1Id),
                        eqCtgL2Id(ctgL2Id),
                        eqCtgL3Id(ctgL3Id),
                        eqCtgL4Id(ctgL4Id),
                        goeMinPrice(minPrice),
                        loeMaxPrice(maxPrice))
                .distinct()
                .fetchOne();

    }

    // ????????????
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

    // ???????????? count(*) -> ?????? ??????. ????????? ?????? ???????????? ????????? ??????
    @Override
    public Long countAllBoardByQuery(String query, Long minPrice, Long maxPrice) {
        return queryFactory.select(productBoard.count())
                .from(productBoard)
                .join(productBoard.tag, tag)
                .where(tag.keyword.equalsIgnoreCase(query)
                                .or(productBoard.title.containsIgnoreCase(query))
                                .or(productBoard.pdtName.containsIgnoreCase(query)),
                        goeMinPrice(minPrice),
                        loeMaxPrice(maxPrice))
                .distinct()
                .fetchOne();
    }

    @Override
    public List<MainProduct> findAllMainProductByOption(Long boardId, String option1) {
        return queryFactory.selectFrom(mainProduct)
                .join(mainProduct.productBoard, productBoard)
                .where(productBoard.id.eq(boardId),
                        mainProduct.optionValue1.eq(option1))
                .orderBy(mainProduct.optionValue2.desc())
                .fetch();
    }

    @Override
    public void increaseMainProductStock(Long pdtId, Integer amount) {
        queryFactory.update(mainProduct)
                .set(mainProduct.stock, mainProduct.stock.add(amount))
                .where(mainProduct.id.eq(pdtId))
                .execute();
    }

    @Override
    public void decreaseMainProductStock(Long pdtId, Integer amount) {
        queryFactory.update(mainProduct)
                .set(mainProduct.stock, mainProduct.stock.add(-amount))
                .where(mainProduct.id.eq(pdtId))
                .execute();
    }

    private BooleanExpression eqUserId(Long id) {
        return id == null ? null : user.id.eq(id);
    }

    private BooleanExpression eqCtgL1Id(Long id) {
        return id == null ? null : categoryL1.id.eq(id);
    }

    private BooleanExpression eqCtgL2Id(Long id) {
        return id == null ? null : categoryL2.id.eq(id);
    }

    private BooleanExpression eqCtgL3Id(Long id) {
        return id == null ? null : categoryL3.id.eq(id);
    }

    private BooleanExpression eqCtgL4Id(Long id) {
        return id == null ? null : categoryL4.id.eq(id);
    }

    private BooleanExpression goeMinPrice(Long minPrice) {
        return minPrice == null ? null : productBoard.minPrice.goe(minPrice);
    }

    private BooleanExpression loeMaxPrice(Long maxPrice) {
        return maxPrice == null ? null : productBoard.minPrice.loe(maxPrice);
    }

    private OrderSpecifier<?> sortExpression(String sort) {
        switch (sort) {
            case "?????????":
                return productBoard.salesVol.desc();
            case "????????????":
                return productBoard.minPrice.asc();
            case "????????????":
                return productBoard.minPrice.desc();
            case "????????????":
                return productBoard.createdAt.desc();
            case "???????????????":
                return productBoard.totalReviewQty.desc();
            default:
                return productBoard.totalScore.desc();
        }
    }
}
