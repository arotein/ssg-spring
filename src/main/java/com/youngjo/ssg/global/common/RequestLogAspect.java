package com.youngjo.ssg.global.common;

import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RequestLogAspect {
    private final ClientInfoLoader clientInfoLoader;

    @Around("execution(* *..*Controller.*(..))")
    public Object requestLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (clientInfoLoader.isAnonymous() == true) {
            log.info("{} {} [ANONYMOUS]", request.getMethod(), request.getRequestURI());
        } else {
            log.info("[{}] {} [USER] {}", request.getMethod(), request.getRequestURI(), clientInfoLoader.getUserName());
        }
        return joinPoint.proceed();
    }
}
