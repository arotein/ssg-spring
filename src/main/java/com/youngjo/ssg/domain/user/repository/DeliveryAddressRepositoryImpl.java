package com.youngjo.ssg.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.user.domain.DeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QAddress.address;
import static com.youngjo.ssg.domain.user.domain.QDeliveryAddress.deliveryAddress;
import static com.youngjo.ssg.domain.user.domain.QUser.user;

@Repository
public class DeliveryAddressRepositoryImpl implements DeliveryAddressRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public DeliveryAddressRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public DeliveryAddress saveDeliveryAddress(DeliveryAddress address) {
        entityManager.persist(address);
        return address;
    }

    @Override
    public List<DeliveryAddress> findAllDeliveryAddress(Long userId) {
        return queryFactory.selectFrom(deliveryAddress)
                .join(deliveryAddress.recipientAddress, address).fetchJoin()
                .join(deliveryAddress.user, user).on(user.id.eq(userId))
                .fetch();
    }

    @Override
    public DeliveryAddress findDeliveryAddressById(Long userId, Long deliveryAddrId) {
        return queryFactory.selectFrom(deliveryAddress)
                .join(deliveryAddress.recipientAddress, address).fetchJoin()
                .join(deliveryAddress.user, user).on(user.id.eq(userId))
                .where(deliveryAddress.id.eq(deliveryAddrId))
                .fetchOne();
    }

    @Override
    public DeliveryAddress findMainDeliveryAddressById(Long userId) {
        return queryFactory.selectFrom(deliveryAddress)
                .join(deliveryAddress.user, user).on(user.id.eq(userId))
                .where(deliveryAddress.isMain.isTrue())
                .fetchOne();
    }

    @Override
    public void removeDeliveryAddressById(Long userId, Long deliveryAddrId) {
        entityManager.remove(
                queryFactory.selectFrom(deliveryAddress)
                        .join(deliveryAddress.user, user).on(user.id.eq(userId))
                        .where(deliveryAddress.id.eq(deliveryAddrId),
                                deliveryAddress.isMain.isFalse())
                        .fetchOne());
    }
}
