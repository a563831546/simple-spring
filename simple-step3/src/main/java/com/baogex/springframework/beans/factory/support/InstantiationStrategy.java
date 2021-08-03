package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * <p>
 * 实例化策略
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-03
 */
public interface InstantiationStrategy {
    /**
     * 实例化一个对象
     *
     * @param beanDefinition bean定义信息
     * @param beanName       bean名称
     * @param ctor           构造函数信息
     * @param args           参数
     * @return 返回一个创建好的实例对象
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
