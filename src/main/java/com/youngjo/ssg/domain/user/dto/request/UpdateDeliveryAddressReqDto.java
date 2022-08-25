package com.youngjo.ssg.domain.user.dto.request;

import com.youngjo.ssg.domain.user.dto.UserStaticDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdateDeliveryAddressReqDto {
    private Long deliveryAddrId;
    @NotBlank
    private String alias;
    @NotBlank
    private String recipientName;
    @NotBlank
    private String phoneNumber;
    private String secondContactNumber;
    private UserStaticDto.AddressReqDto recipientAddress;
}
