package com.youngjo.ssg.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.global.enumeration.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.youngjo.ssg.domain.user.domain.QUser.user;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Long saveUser(User user) {
        entityManager.persist(user);
        return user.getId();
    }

    @Override
    public User findUserById(Long id) {
        return queryFactory.selectFrom(user)
                .where(user.id.eq(id),
                        user.status.eq(UserStatus.ENABLED))
                .fetchOne();
    }

    @Override
    public User findUserByLoginId(String loginId) {
        return queryFactory.selectFrom(user)
                .where(user.loginId.eq(loginId),
                        user.status.eq(UserStatus.ENABLED))
                .fetchOne();
    }

    @Override
    public User findUserByEmail(String email) {
        return queryFactory.selectFrom(user)
                .where(user.email.eq(email),
                        user.status.eq(UserStatus.ENABLED))
                .fetchOne();
    }
}