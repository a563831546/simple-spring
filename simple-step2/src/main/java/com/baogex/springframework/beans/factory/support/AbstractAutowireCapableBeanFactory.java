package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.beans.factory.BeansException;
import com.baogex.springframework.beans.factory.config.BeanDefinition;

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
