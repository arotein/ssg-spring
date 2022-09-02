package com.youngjo.ssg.global.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommonResponse<T> {
    private T data;
    private Integer errorCode;
    private String errorMessage;
}
/***
 * 1 : Expired Token
 * 2 : Invalid Signature
 * 3 : Access Denied
 * 4 : 잘못된 값
 * 400 : 400 BAD_REQUEST
 * 405 : 405 METHOD_NOT_ALLOWED
 */