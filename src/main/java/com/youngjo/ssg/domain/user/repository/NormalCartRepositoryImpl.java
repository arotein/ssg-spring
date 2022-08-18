package com.youngjo.ssg.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.user.domain.NormalCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QMainProduct.mainProduct;
import static com.youngjo.ssg.domain.user.domain.QNormalCart.normalCart;
import static com.youngjo.ssg.domain.user.domain.QUser.user;

@Repository
public class NormalCartRepositoryImpl implements NormalCartRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public NormalCartRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Long saveNormalCart(NormalCart normalCart) {
        entityManager.persist(normalCart);
        return normalCart.getId();
    }

    @Override
    public List<NormalCart> findAllNormalCartByUserId(Long userId) {
        return queryFactory.selectFrom(normalCart)
                .join(normalCart.mainProduct).fetchJoin()
                .join(normalCart.user, user)
                .where(user.id.eq(userId))
                .distinct()
                .fetch();
    }

    @Override
    public void removePdtInUserCart(List<Long> pdtIds, Long userId) {
        queryFactory.selectFrom(normalCart)
                .join(normalCart.user, user)
                .join(normalCart.mainProduct, mainProduct)
                .where(mainProduct.id.in(pdtIds), user.id.eq(userId))
                .distinct()
                .fetch()
                .forEach(entityManager::remove);
    }

    @Override
    public NormalCart findPdtInUserCartByPdtId(Long pdtId, Long userId) {
        return queryFactory.selectFrom(normalCart)
                .join(normalCart.user, user)
                .join(normalCart.mainProduct, mainProduct)
                .where(user.id.eq(userId), mainProduct.id.in(pdtId))
                .distinct()
                .fetchOne();
    }

    @Override
    public void removeSoldOutPdtInUserCart(Long userId) {
        queryFactory.selectFrom(normalCart)
                .join(normalCart.user, user)
                .join(normalCart.mainProduct, mainProduct)
                .where(user.id.eq(userId),
                        mainProduct.stock.eq(0))
                .distinct()
                .fetch()
                .forEach(entityManager::remove);
    }
}
