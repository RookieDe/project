package com.project.project5.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 * 跳过日志注解
 * @author chenhongde
 * @ClassName PassLogger
 * @date 2020/4/2 16:40
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassLogger {
    boolean passRequest() default true;
    boolean passResponse() default true;
}
