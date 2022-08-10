package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.CategoryL4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QCategoryL4.categoryL4;

@Repository
public class CategoryL4RepositoryImpl implements CategoryL4Repository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CategoryL4RepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Long save(CategoryL4 categoryL4) {
        entityManager.persist(categoryL4);
        return categoryL4.getId();
    }

    @Override
    public CategoryL4 findById(Long id) {
        return queryFactory.selectFrom(categoryL4)
                .where(categoryL4.id.eq(id))
                .fetchOne();
    }

    @Override
    public CategoryL4 findByName(String name) {
        return queryFactory.selectFrom(categoryL4)
                .where(categoryL4.name.eq(name))
                .fetchOne();
    }

    @Override
    public List<CategoryL4> getAll() {
        return queryFactory.selectFrom(categoryL4).fetch();
    }

    @Override
    public List<CategoryL4> getAllByIdSameL3(Long id) { // N+1문제 고치기
        return queryFactory.select(categoryL4.categoryL3)
                .from(categoryL4)
                .where(categoryL4.id.eq(id))
                .fetchOne().getCategoryL4List();
    }
}
