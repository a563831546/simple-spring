package com.baogex.springframework.util;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = ClassUtils.class.getClassLoader();
        }
        return loader;
    }
}
