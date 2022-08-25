package com.youngjo.ssg.domain.event.handler;

import com.youngjo.ssg.domain.event.dto.PurchaseEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseEventHandler {

    @EventListener
    public void process(PurchaseEventDto eventDto) {

    }
}
