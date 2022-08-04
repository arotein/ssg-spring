package com.youngjo.ssg.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.Category;
import com.youngjo.ssg.domain.product.dto.response.CtgL1L2Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QCategory.category;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CategoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Category> findCtgAll() {
        return queryFactory.selectFrom(category).fetch();
    }

    @Override
    public Category findCtgById(Long id) {
        return queryFactory.selectFrom(category)
                .where(category.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<CtgL1L2Dto> findAllCtgL1L2Dto() {
        List<CtgL1L2Dto> result = new ArrayList<>();
        List<Category> ctgAll = findCtgAll();
        ctgAll.forEach(e -> {
            result.add(CtgL1L2Dto.builder()
                    .ctgL1(e)
                    .ctgL2List(e.getCategoryMList())
                    .build());
        });
        return result;
    }
}
