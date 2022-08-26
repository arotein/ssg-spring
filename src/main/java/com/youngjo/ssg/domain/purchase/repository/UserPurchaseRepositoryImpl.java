package com.youngjo.ssg.domain.purchase.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.purchase.domain.UserPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserPurchaseRepositoryImpl implements UserPurchaseRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public UserPurchaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public void saveUserPurchase(UserPurchase userPurchase) {
        entityManager.persist(userPurchase);
    }
}
