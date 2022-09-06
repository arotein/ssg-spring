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
    private ThreadLocal<Long> time = new ThreadLocal<>();

    @Around("execution(* com.youngjo.ssg..*Controller.*(..))")
    public Object requestControllerLog(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            time.set(System.currentTimeMillis());
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            Object target = joinPoint.getTarget();
            if (clientInfoLoader.isAnonymous() == true) {
                log.info("[ANONYMOUS] {} {} {}", request.getMethod(), request.getRequestURI(), target.getClass().getSimpleName());
            } else {
                log.info("[{} {}] {} {} > {}", clientInfoLoader.getUserId(), clientInfoLoader.getUserName(), request.getMethod(), request.getRequestURI(), target.getClass().getSimpleName());
            }
            Object proceed = joinPoint.proceed();
            long execTime = System.currentTimeMillis() - time.get();
            if (clientInfoLoader.isAnonymous() == true) {
                log.info("[ANONYMOUS] {} {} {} [{}ms]", request.getMethod(), request.getRequestURI(), target.getClass().getSimpleName(), System.currentTimeMillis() - time.get());
            } else {
                log.info("[{} {}] {} {} < {} [{}ms]", clientInfoLoader.getUserId(), clientInfoLoader.getUserName(), request.getMethod(), request.getRequestURI(), target.getClass().getSimpleName(),
                        execTime);
            }
            if (execTime > 5000L) {
                log.warn("{} {} {} execution time: {}ms", request.getMethod(), request.getRequestURI(), target.getClass().getSimpleName(), execTime);
            } else if (execTime > 10000L) {
                log.error("{} {} {} execution time: {}ms", request.getMethod(), request.getRequestURI(), target.getClass().getSimpleName(), execTime);
            }
            return proceed;
        } catch (Exception exception) {
            throw exception;
        } finally {
            time.remove();
        }
    }
}
