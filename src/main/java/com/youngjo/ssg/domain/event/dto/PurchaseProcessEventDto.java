package com.youngjo.ssg.domain.event.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class PurchaseProcessEventDto {
    private final Map<Long, Integer> pdtStockUpdateMap = new HashMap<>(); // <pdtId, amount>

    public PurchaseProcessEventDto(Map<Long, Integer> purchasePdtIdMap) {
        this.pdtStockUpdateMap.putAll(purchasePdtIdMap);
    }
}
