package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.CategoryL2;
import com.youngjo.ssg.domain.product.domain.QCategoryL2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QCategoryL2.categoryL2;
import static com.youngjo.ssg.domain.product.domain.QCategoryL3.categoryL3;

@Repository
public class CategoryL2RepositoryImpl implements CategoryL2Repository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private final QCategoryL2 ctgL2Sub = new QCategoryL2("ctgL2Sub");

    @Autowired
    public CategoryL2RepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Long save(CategoryL2 categoryL2) {
        entityManager.persist(categoryL2);
        return categoryL2.getId();
    }

    @Override
    public CategoryL2 findByName(String name) {
        return queryFactory.selectFrom(categoryL2)
                .where(categoryL2.name.eq(name))
                .fetchOne();
    }

    @Override
    public List<CategoryL2> getAllByL3IdSameL1(Long id) {
        return queryFactory.selectFrom(categoryL2)
                .join(categoryL2.categoryL3List).fetchJoin()
                .where(categoryL2.categoryL1.id.eq(
                        JPAExpressions.select(categoryL3.categoryL2.categoryL1.id)
                                .from(categoryL3)
                                .where(categoryL3.id.eq(id))
                ))
                .distinct()
                .fetch();
    }

    @Override
    public List<CategoryL2> getAllByIdSameL1(Long id) {
        return queryFactory.selectFrom(categoryL2)
                .join(categoryL2.categoryL3List).fetchJoin()
                .where(categoryL2.categoryL1.id.eq(
                        JPAExpressions.select(ctgL2Sub.categoryL1.id)
                                .from(ctgL2Sub)
                                .where(ctgL2Sub.id.eq(id))))
                .distinct()
                .fetch();
    }
}
