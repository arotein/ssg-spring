package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.Category;
import com.youngjo.ssg.domain.product.domain.CategoryM;
import com.youngjo.ssg.domain.product.domain.CategoryS;
import com.youngjo.ssg.domain.product.domain.CategorySS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.youngjo.ssg.domain.product.domain.QCategory.category;
import static com.youngjo.ssg.domain.product.domain.QCategoryM.categoryM;
import static com.youngjo.ssg.domain.product.domain.QCategoryS.categoryS;
import static com.youngjo.ssg.domain.product.domain.QCategorySS.categorySS;

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
    public void addCategory(Category category) {
        entityManager.persist(category);
    }

    @Override
    public void addCategoryM(CategoryM categoryM) {
        entityManager.persist(categoryM);
    }

    @Override
    public void addCategoryS(CategoryS categoryS) {
        entityManager.persist(categoryS);
    }

    @Override
    public void addCategorySS(CategorySS categorySS) {
        entityManager.persist(categorySS);
    }

    @Override
    public Category findCategoryByName(String name) {
        return queryFactory.selectFrom(category)
                .where(category.name.eq(name))
                .fetchOne();
    }

    @Override
    public CategoryM findCategoryMByName(String name) {
        return queryFactory.selectFrom(categoryM)
                .where(categoryM.name.eq(name))
                .fetchOne();
    }

    @Override
    public CategoryS findCategorySByName(String name) {
        return queryFactory.selectFrom(categoryS)
                .where(categoryS.name.eq(name))
                .fetchOne();
    }

    @Override
    public CategorySS findCategorySSByName(String name) {
        return queryFactory.selectFrom(categorySS)
                .where(categorySS.name.eq(name))
                .fetchOne();
    }
}
