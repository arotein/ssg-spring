package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.CategoryL1;
import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.domain.CategoryL3;
import com.youngjo.ssg.domain.product.domain.CategoryL4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QCategoryL1.categoryL1;
import static com.youngjo.ssg.domain.product.domain.QCategoryL2.categoryL2;
import static com.youngjo.ssg.domain.product.domain.QCategoryL3.categoryL3;
import static com.youngjo.ssg.domain.product.domain.QCategoryL4.categoryL4;


@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CategoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    // ==service code==
    @Override
    public List<CategoryL1> findCtgL1All() {
        return queryFactory.selectFrom(categoryL1).fetch();
    }

    @Override
    public CategoryL1 findCtgById(Long id) {
        return queryFactory.selectFrom(categoryL1)
                .where(categoryL1.id.eq(id))
                .fetchOne();
    }

    @Override
    public CategoryL1 findCtgL1ByName(String name) {
        return queryFactory.selectFrom(categoryL1)
                .where(categoryL1.name.eq(name))
                .fetchOne();
    }

    @Override
    public CategoryL2 findCtgL2ByName(String name) {
        return queryFactory.selectFrom(categoryL2)
                .where(categoryL2.name.eq(name))
                .fetchOne();
    }

    @Override
    public CategoryL3 findCtgL3ByName(String name) {
        return queryFactory.selectFrom(categoryL3)
                .where(categoryL3.name.eq(name))
                .fetchOne();
    }

    @Override
    public CategoryL4 findCtgL4ByName(String name) {
        return queryFactory.selectFrom(categoryL4)
                .where(categoryL4.name.eq(name))
                .fetchOne();
    }

    // ==dev code==
    @Override
    public void addCtgL3(CategoryL3 categoryL3) {
        entityManager.persist(categoryL3);
    }

    @Override
    public void addCtgL4(CategoryL4 categoryL4) {
        entityManager.persist(categoryL4);
    }
}
