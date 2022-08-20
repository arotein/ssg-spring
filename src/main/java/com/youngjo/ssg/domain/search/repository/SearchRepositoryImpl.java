package com.youngjo.ssg.domain.search.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.search.domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.youngjo.ssg.domain.search.domain.QSearch.search;

@Repository
public class SearchRepositoryImpl implements SearchRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public SearchRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public void saveSearch(Search search) {
        entityManager.persist(search);
    }

    @Override
    public Search findSearch(String query) {
        return queryFactory.selectFrom(search)
                .where(search.query.eq(query))
                .fetchOne();
    }
}
