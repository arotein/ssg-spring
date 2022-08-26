package com.youngjo.ssg.domain.event.dto;

import lombok.Getter;

@Getter
public class PurchaseProcessEventDto {
    private final Long userPurchaseId;

    public PurchaseProcessEventDto(Long userPurchaseId) {
        this.userPurchaseId = userPurchaseId;
    }
}
