package com.youngjo.ssg.domain.event.handler;

import com.youngjo.ssg.domain.event.dto.PurchaseProcessEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseEventProcessHandler {
    private final ProductStockHandler productStockHandler;

    @Async
    @TransactionalEventListener
    public void purchaseProcess(PurchaseProcessEventDto eventDto) {
        log.info("Purchase process event occurred");
        for (Map.Entry<Long, Integer> pdtMap : eventDto.getPdtStockUpdateMap().entrySet()) {
            try {
                productStockHandler.decreaseAmount(pdtMap.getKey(), pdtMap.getValue());
            } catch (DataIntegrityViolationException ex) {
                log.error("Purchase out of stock event occurred. MainProduct id = {}", pdtMap.getKey());
                // 상품 재고부족처리 이벤트 발생 -> 미구현
            }
        }
    }
}
