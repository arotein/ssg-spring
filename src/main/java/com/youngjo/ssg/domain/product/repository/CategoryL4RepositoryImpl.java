package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.CategoryL4;
import com.youngjo.ssg.domain.product.domain.QCategoryL4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QCategoryL2.categoryL2;
import static com.youngjo.ssg.domain.product.domain.QCategoryL3.categoryL3;
import static com.youngjo.ssg.domain.product.domain.QCategoryL4.categoryL4;

@Repository
public class CategoryL4RepositoryImpl implements CategoryL4Repository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private final QCategoryL4 ctgL4Sub = new QCategoryL4("ctgL4Sub");

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
    public CategoryL4 findByL2L3L4Name(String ctgL2Name, String ctgL3Name, String ctgL4Name) {
        return queryFactory.selectFrom(categoryL4)
                .join(categoryL4.categoryL3, categoryL3)
                .join(categoryL3.categoryL2, categoryL2)
                .where(categoryL4.name.eq(ctgL4Name),
                        categoryL3.name.eq(ctgL3Name),
                        categoryL2.name.eq(ctgL2Name)
                )
                .fetchOne();
    }

    @Override
    public List<CategoryL4> getAll() {
        return queryFactory.selectFrom(categoryL4).fetch();
    }

    @Override
    public List<CategoryL4> getAllByIdSameL3(Long id) {
        return queryFactory.selectFrom(categoryL4)
                .where(categoryL4.categoryL3.id.eq(
                        JPAExpressions.select(ctgL4Sub.categoryL3.id)
                                .from(ctgL4Sub)
                                .where(ctgL4Sub.id.eq(id))))
                .fetch();
    }
}
