package com.baogex.springframework.apo;

/**
 * <p>
 *
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-10
 */
public interface ClassFilter {

    /**
     * 切入点应该应用于给定的接口还是目标类
     *
     * @param clazz 目标类
     * @return 是否匹配
     */
    boolean matches(Class<?> clazz);

}
