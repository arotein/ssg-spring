package com.youngjo.ssg.domain.user.controller;

import com.youngjo.ssg.domain.user.dto.request.AddDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.request.DeliveryAddressIdReqDto;
import com.youngjo.ssg.domain.user.dto.request.UpdateDeliveryAddressReqDto;
import com.youngjo.ssg.domain.user.dto.response.MyDeliveryAddressResDto;
import com.youngjo.ssg.domain.user.service.MyDeliveryAddressService;
import com.youngjo.ssg.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/my/deliveryAddr")
@RequiredArgsConstructor
public class MyDeliveryAddressController {
    private final MyDeliveryAddressService myDeliveryAddressService;

    // == User 전용 기능 ==
    // 내 배송지 추가
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public CommonResponse addDeliveryAddress(@RequestBody AddDeliveryAddressReqDto dto) {
        return CommonResponse.builder()
                .data(myDeliveryAddressService.addDeliveryAddress(dto))
                .build();
    }

    // 내 배송지 조회
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public CommonResponse getDeliveryAddress() {
        List<MyDeliveryAddressResDto> resList = myDeliveryAddressService.getMyDeliveryAddressList();
        resList.forEach(e -> e.setListIndex(resList.indexOf(e)));
        return CommonResponse.builder()
                .data(resList)
                .build();
    }

    // 내 배송지 수정 -> 수정되지 않은 값도 다 받음
    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    public CommonResponse updateDeliveryAddress(@Validated @RequestBody UpdateDeliveryAddressReqDto dto) {
        return CommonResponse.builder()
                .data(myDeliveryAddressService.updateDeliveryAddress(dto))
                .build();
    }

    // 기본 배송지로 설정
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/main")
    public CommonResponse switchingMainDeliveryAddress(@Validated @RequestBody DeliveryAddressIdReqDto dto) {
        return CommonResponse.builder()
                .data(myDeliveryAddressService.switchingMainDeliveryAddress(dto))
                .build();
    }

    // 내 배송지 삭제
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("")
    public CommonResponse delDeliveryAddress(@Validated @RequestBody DeliveryAddressIdReqDto dto) {
        return CommonResponse.builder()
                .data(myDeliveryAddressService.delDeliveryAddress(dto))
                .build();
    }
}
