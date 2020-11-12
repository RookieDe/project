package com.example.demo.config.annotation;

import java.lang.annotation.*;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName MyTarget
 * @date 2020/10/19 13:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MyTarget {
    String name() default "zhangsan";
    int ages() default 22;

}
