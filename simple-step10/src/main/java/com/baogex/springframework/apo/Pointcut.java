package com.baogex.springframework.apo;

/**
 * <p>
 *
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-10
 */
public interface Pointcut {

    /**
     * 获取类过滤器
     */
    ClassFilter getClassFilter();

    /**
     * 获取函数匹配器
     */
    MethodMatcher getMethodMatcher();
}
