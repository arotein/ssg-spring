package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QHappyLoungeItem.happyLoungeItem;
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

    @Override
    public ProductBoard findBoard() {
        return queryFactory.selectFrom(productBoard)
                .fetchFirst();
    }

    @Override
    public List<HappyLoungeItem> findHappyLoungeItems(Integer qty) {
        return queryFactory.selectFrom(happyLoungeItem)
                .limit(qty)
                .offset(0)
                .fetch();
    }

    @Override
    public void addCategory(CategoryL1 categoryL1) {
        entityManager.persist(categoryL1);
    }

    @Override
    public void addCategoryM(CategoryL2 categoryL2) {
        entityManager.persist(categoryL2);
    }

    @Override
    public void addCategoryS(CategoryL3 categoryL3) {
        entityManager.persist(categoryL3);
    }

    @Override
    public void addCategorySS(CategoryL4 categoryL4) {
        entityManager.persist(categoryL4);
    }
}
