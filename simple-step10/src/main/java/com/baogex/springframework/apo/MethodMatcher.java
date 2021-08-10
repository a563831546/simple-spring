package com.baogex.springframework.apo;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * 函数匹配器
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-10
 */
public interface MethodMatcher {
    
  boolean  matches(Method method,Class<?> targetClass);
} 
