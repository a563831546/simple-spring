package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 简单实例化策略
 *
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    /**
     * 实例化一个对象
     *
     * @param beanDefinition bean定义信息
     * @param beanName       bean名称
     * @param ctor           构造函数信息
     * @param args           参数
     * @return 返回一个创建好的实例对象
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args) throws BeansException {
        // 1.入参校验
        if (beanDefinition != null) {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (beanClass != null) {
                try {
                    return ctor != null ? beanClass.getConstructor(ctor.getParameterTypes()).newInstance()
                            : beanClass.getConstructor().newInstance();
                } catch (Exception e) {
                    throw new BeansException("Simple example factory--Instantiate object[" + beanName + "]fail，" + e.getMessage());
                }
            }
        }

        return null;
    }
}
