package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.CategoryL3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QCategoryL3.categoryL3;
import static com.youngjo.ssg.domain.product.domain.QCategoryL4.categoryL4;

@Repository
public class CategoryL3RepositoryImpl implements CategoryL3Repository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CategoryL3RepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Long save(CategoryL3 categoryL3) {
        entityManager.persist(categoryL3);
        return categoryL3.getId();
    }

    @Override
    public CategoryL3 findByName(String name) {
        return queryFactory.selectFrom(categoryL3)
                .where(categoryL3.name.eq(name))
                .fetchOne();
    }

    @Override
    public CategoryL3 findByNameRelatedCtgL2(String nameL2, String nameL3) {
        return queryFactory.selectFrom(categoryL3)
                .where(categoryL3.name.eq(nameL3),
                        categoryL3.categoryL2.name.eq(nameL2))
                .fetchOne();
    }

    @Override
    public List<CategoryL3> getAllByL4IdSameL2(Long id) { // N+1문제 고치기
        return queryFactory.select(categoryL3.categoryL2)
                .from(categoryL3)
                .where(categoryL3.id.eq(
                        JPAExpressions.select(categoryL4.categoryL3.id)
                                .from(categoryL4)
                                .where(categoryL4.id.eq(id))
                )).fetchOne().getCategoryL3List();
    }

    @Override
    public List<CategoryL3> getAllByIdSameL2(Long id) { // N+1문제 고치기
        return queryFactory.select(categoryL3.categoryL2)
                .from(categoryL3)
                .where(categoryL3.id.eq(id))
                .fetchOne().getCategoryL3List();
    }
}
