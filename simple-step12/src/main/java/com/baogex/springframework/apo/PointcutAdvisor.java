package com.baogex.springframework.apo;

/**
 * <p>
 *
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-11
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
