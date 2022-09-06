package com.youngjo.ssg.global.exception;

import com.youngjo.ssg.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity runtimeExHandle(RuntimeException ex) {
        log.error("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.ok()
                .body(CommonResponse.builder().errorCode(5).errorMessage(ex.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity accessDeniedExHandle(AccessDeniedException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(CommonResponse.builder().errorCode(3).errorMessage(ex.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity methodNotAllowedExHandle(HttpRequestMethodNotSupportedException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(CommonResponse.builder().errorCode(405).errorMessage(ex.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity illegalArgumentExceptionExHandle(IllegalArgumentException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.builder().errorCode(400).errorMessage(ex.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity constraintViolationExceptionExceptionExHandle(ConstraintViolationException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.builder().errorCode(5).errorMessage("중복된 값입니다.").build());
    }
}