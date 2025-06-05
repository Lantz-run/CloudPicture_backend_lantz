package com.lantz.lantzpicturebackend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Project: lantz-picture-backend
 * <p>Powered by Lantz On 2025/6/5
 *
 * @author Lantz
 * @version 1.0
 * @Description AuthCheck
 * @since 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 必须有某个角色
     * @return
     */
    String mustRole() default "";
}
