package com.baogex.springframework.beans.factory.config;

import com.baogex.springframework.beans.BeansException;

/**
 * <p>
 *
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-04
 */
public interface BeanPostProcessor {
    /**
     * 在bean执行初始化前执行
     *
     * @param bean     目标bean对象
     * @param beanName bean名称
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在bean执行初始化后执行
     *
     * @param bean     目标bean对象
     * @param beanName bean名称
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;


}
