package com.baogex.springframework.beans.factory.advice;

import com.baogex.springframework.apo.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author : baogex.com
 * @since : 2021-08-11
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("拦截方法：" + method.getName());

    }
}
