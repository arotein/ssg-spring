package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.ProductBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QCategoryL2.categoryL2;
import static com.youngjo.ssg.domain.product.domain.QCategoryL3.categoryL3;
import static com.youngjo.ssg.domain.product.domain.QCategoryL4.categoryL4;
import static com.youngjo.ssg.domain.product.domain.QProductBoard.productBoard;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    // == service code ==
    @Override
    public Long save(ProductBoard productBoard) {
        entityManager.persist(productBoard);
        return productBoard.getId();
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

    // == dev code ==
    @Override
    public ProductBoard findBoardFirst(Long id) {
        return queryFactory.selectFrom(productBoard)
                .where(productBoard.id.eq(id))
                .fetchOne();
    }
}
