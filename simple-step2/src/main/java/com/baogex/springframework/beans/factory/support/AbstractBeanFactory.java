package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.beans.factory.BeanFactory;
import com.baogex.springframework.beans.factory.BeansException;
import com.baogex.springframework.beans.factory.config.BeanDefinition;

/**
 * 抽象bean工厂，继承了DefaultSingletonBeanRegistry，
 *
 * @author : baogex.com
 * @since : 2021-08-02
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
        // 1.先去实例缓存中获取单例对象
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }
        // 2.找不到则开始创建bean实例
        return createBean(beanName, getBeanDefinition(beanName));
    }
    /* ============================抽象定义============================*/

    /**
     * 根据bean名称检索获取一个bean定义对象
     *
     * @param beanName bean名称
     * @return bean定义对象
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 根据传入的一个beanDefinition创建出一个bean
     *
     * @param beanName bean名称
     * @return 一个bean定义
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
