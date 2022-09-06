package com.youngjo.ssg.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.user.domain.MyDeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.youngjo.ssg.domain.user.domain.QAddress.address;
import static com.youngjo.ssg.domain.user.domain.QMyDeliveryAddress.myDeliveryAddress;
import static com.youngjo.ssg.domain.user.domain.QUser.user;

@Repository
public class MyDeliveryAddressRepositoryImpl implements MyDeliveryAddressRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public MyDeliveryAddressRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public MyDeliveryAddress saveMyDeliveryAddress(MyDeliveryAddress address) {
        entityManager.persist(address);
        return address;
    }

    @Override
    public List<MyDeliveryAddress> findAllMyDeliveryAddress(Long userId) {
        return queryFactory.selectFrom(myDeliveryAddress)
                .join(myDeliveryAddress.recipientAddress, address).fetchJoin()
                .join(myDeliveryAddress.user, user).on(user.id.eq(userId))
                .fetch();
    }

    @Override
    public MyDeliveryAddress findMyDeliveryAddressById(Long userId, Long myDeliAddrId) {
        return queryFactory.selectFrom(myDeliveryAddress)
                .join(myDeliveryAddress.recipientAddress, address).fetchJoin()
                .join(myDeliveryAddress.user, user).on(user.id.eq(userId))
                .where(myDeliveryAddress.id.eq(myDeliAddrId))
                .fetchOne();
    }

    @Override
    public MyDeliveryAddress findMainMyDeliveryAddressByUserId(Long userId) {
        return queryFactory.selectFrom(myDeliveryAddress)
                .join(myDeliveryAddress.user, user).on(user.id.eq(userId))
                .where(myDeliveryAddress.isMain.isTrue())
                .fetchOne();
    }

    @Override
    public void removeMyDeliveryAddressById(Long userId, Long addrId) {
        entityManager.remove(
                queryFactory.selectFrom(myDeliveryAddress)
                        .join(myDeliveryAddress.user, user).on(user.id.eq(userId))
                        .where(myDeliveryAddress.id.eq(addrId),
                                myDeliveryAddress.isMain.isFalse())
                        .fetchOne());
    }
}
