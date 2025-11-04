package com.lantz.lantzpicturebackend.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * <p>Powered by Lantz On 2025/10/15
 *
 * @author Lantz
 * @version 1.0
 * @Description LogInterceptor 请求响应日志
 * @since 1.8
 */
@Aspect
@Component
@Slf4j
public class LogInterceptor {

    /**
     * 执行拦截
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.lantz.lantzpicturebackend.controller.*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 获取请求路径
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 生成唯一请求id
        String requestId = UUID.randomUUID().toString();
        String url = servletRequest.getRequestURI();

        // 获取请求参数
        Object[] args = joinPoint.getArgs();
        String reqParams = "[" + StringUtils.join(args, ", ") + "]";

        // 输出请求日志
        log.info("request start，id: {}, path: {}, ip: {}, params: {}", requestId, url,
                servletRequest.getRemoteHost(), reqParams);

        // 执行原方法
        Object result = joinPoint.proceed();

        // 输出原日志
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        log.info("request end, id: {}, cost: {}ms", requestId, totalTimeMillis);

        return result;
    }
}
