package com.baogex.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @author : baogex.com
 * @since : 2021-08-13
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    String value() default "singleton";
}
