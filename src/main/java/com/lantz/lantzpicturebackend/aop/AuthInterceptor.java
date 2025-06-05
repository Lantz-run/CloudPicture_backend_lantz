package com.lantz.lantzpicturebackend.aop;

import com.lantz.lantzpicturebackend.annotation.AuthCheck;
import com.lantz.lantzpicturebackend.exception.BusinessException;
import com.lantz.lantzpicturebackend.exception.ErrorCode;
import com.lantz.lantzpicturebackend.model.entity.User;
import com.lantz.lantzpicturebackend.model.enums.UserRoleEnum;
import com.lantz.lantzpicturebackend.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Project: lantz-picture-backend
 * <p>Powered by Lantz On 2025/6/5
 *
 * @author Lantz
 * @version 1.0
 * @Description AuthInterceptor
 * @since 1.8
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     * @param joinPoint 拦截点
     * @param authCheck 权限校验注解
     * @return
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 不需要权限的，直接放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }

        // 以下是需要权限的才给放行
        // 获取当前用户角色
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        // 没有权限，拒绝
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 要求必须有管理员权限，但是用户没有管理员权限，拒绝
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum) && !UserRoleEnum.ADMIN.equals(userRoleEnum) ) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 权限校验通过，放行
        return joinPoint.proceed();
    }
}
