package com.baogex.simple.spring.step2.support;

import com.baogex.simple.spring.step2.BeansException;
import com.baogex.simple.spring.step2.config.BeanDefinition;

/**
 * @author : baogex.com
 * @since : 2021-08-02
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object instance = null;
        try {
            instance = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("实例化Bean时发生错误", e);
        }
        addSingleton(beanName, instance);
        return instance;
    }
}
