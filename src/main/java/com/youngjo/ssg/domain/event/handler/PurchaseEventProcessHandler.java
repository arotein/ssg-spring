package com.youngjo.ssg.domain.event.handler;

import com.youngjo.ssg.domain.event.dto.PurchaseProcessEventDto;
import com.youngjo.ssg.domain.purchase.domain.UserPurchase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityManager;

@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseEventProcessHandler {
    private final EntityManager entityManager;
    private final ProductStockHandler productStockHandler;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void purchaseProcess(PurchaseProcessEventDto eventDto) {
        UserPurchase userPurchase = entityManager.createQuery("select up from UserPurchase up " +
                        "join fetch up.purchaseMiddleProductList where up.id=:id", UserPurchase.class)
                .setParameter("id", eventDto.getUserPurchaseId())
                .getSingleResult();

        userPurchase.getPurchaseMiddleProductList()
                .forEach(middle -> productStockHandler.decreaseAmount(middle.getMainProduct().getId(), middle.getPdtQty()));
        userPurchase.setPurchaseStatusToComplete();
    }
}
