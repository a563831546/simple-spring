package com.baogex.springframework.beans.factory.config;

import com.baogex.springframework.beans.BeansException;

/**
 * <p>
 *
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-11
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
