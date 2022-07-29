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
                .setSuccess(false)
                .setErrorMessage(ex.getMessage());
    }
}