package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.ProductBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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

    @Override
    public ProductBoard findBoard() {
        return queryFactory.selectFrom(productBoard)
                .fetchFirst();
    }

    // == dev code ==
}
