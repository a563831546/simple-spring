package com.baogex.springframework.beans.factory.config;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * <p>
 * bean工厂前置处理器
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-04
 */
public interface BeanFactoryPostProcessor {

    /**
     * 加载beanDefinition之后，在实例化bean对象前，支持修改beanDefinition属性机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
