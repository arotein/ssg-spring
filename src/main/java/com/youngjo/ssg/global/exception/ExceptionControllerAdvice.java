package com.youngjo.ssg.global.exception;

import com.youngjo.ssg.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public CommonResponse<String> runtimeExHandle(RuntimeException ex) {
        log.error("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return new CommonResponse<String>()
                .setErrorMessage("문제가 발생했습니다. 지속적으로 발생할 시 관리자에게 문의하세요.");
    }
}