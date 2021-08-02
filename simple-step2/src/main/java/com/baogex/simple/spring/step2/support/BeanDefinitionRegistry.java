package com.baogex.simple.spring.step2.support;

import com.baogex.simple.spring.step2.config.BeanDefinition;

/**
 * bean定义注册接口
 *
 * @author : baogex.com
 * @since : 2021-08-02
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表注册bean对象实例
     * @param beanName bean名称、id
     * @param beanDefinition bean定义实例对象
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
