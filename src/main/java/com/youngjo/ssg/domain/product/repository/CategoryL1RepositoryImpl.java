package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.CategoryL1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QCategoryL1.categoryL1;

@Repository
public class CategoryL1RepositoryImpl implements CategoryL1Repository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CategoryL1RepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Long save(CategoryL1 ctgL1) {
        entityManager.persist(ctgL1);
        return ctgL1.getId();
    }

    @Override
    public CategoryL1 findByName(String name) {
        return queryFactory.selectFrom(categoryL1)
                .where(categoryL1.name.eq(name))
                .fetchOne();
    }

    @Override
    public CategoryL1 findById(Long id) {
        return queryFactory.selectFrom(categoryL1)
                .where(categoryL1.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<CategoryL1> getAll() {
        return queryFactory.selectFrom(categoryL1).fetch();
    }

    @Override
    public void addUrl(Long id, String url) {
        CategoryL1 ctgL1 = findById(id);
        ctgL1.setImgUrl(url);
    }
}
