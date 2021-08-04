package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 大多数可列出的 bean 工厂要实现的配置接口。
 * 除了ConfigurableBeanFactory ，它还提供了分析和修改 bean 定义以及预实例化单例的工具
 *
 * @author : baogex.com
 * @since : 2021-08-04
 */
public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, ListableBeanFactory, AutowireCapableBeanFactory {
    /**
     * 根据bean名称
     * 获取bean定义
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
