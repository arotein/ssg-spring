package com.youngjo.ssg.domain.user.controller;

import com.youngjo.ssg.domain.user.dto.request.AddDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.request.DeliveryAddressIdReqDto;
import com.youngjo.ssg.domain.user.dto.request.UpdateDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.response.DeliveryAddressResDto;
import com.youngjo.ssg.domain.user.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeliveryAddressController {
    private final DeliveryAddressService deliveryAddressService;

    // == User 전용 기능 ==
    // 내 배송지 추가
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/my/deliveryAddr")
    public Boolean addDeliveryAddress(@RequestBody AddDeliveryAddressReqDto dto) {
        deliveryAddressService.addDeliveryAddress(dto);
        return true;
    }

    // 내 배송지 조회
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my/deliveryAddr")
    public List<DeliveryAddressResDto> getDeliveryAddress() {
        return deliveryAddressService.getDeliveryAddress();
    }

    // 내 배송지 수정 -> 수정되지 않은 값도 다 받음
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/my/deliveryAddr")
    public Boolean updateDeliveryAddress(@Validated @RequestBody UpdateDeliveryAddressReqDto dto) {
        deliveryAddressService.updateDeliveryAddress(dto);
        return true;
    }

    // 기본 배송지로 설정
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/my/deliveryAddr/main")
    public Boolean updateMainDeliveryAddress(@Validated @RequestBody DeliveryAddressIdReqDto dto) {
        deliveryAddressService.updateMainDeliveryAddress(dto);
        return true;
    }

    // 내 배송지 삭제
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/my/deliveryAddr")
    public Boolean delDeliveryAddress(@Validated @RequestBody DeliveryAddressIdReqDto dto) {
        deliveryAddressService.delDeliveryAddress(dto);
        return true;
    }
}
