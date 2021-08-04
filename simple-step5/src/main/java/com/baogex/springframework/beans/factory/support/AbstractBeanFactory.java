package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.beans.factory.BeanFactory;
import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.config.BeanPostProcessor;
import com.baogex.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象bean工厂，继承了DefaultSingletonBeanRegistry，
 *
 * @author : baogex.com
 * @since : 2021-08-02
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>(8);

    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected Object doGetBean(final String beanName, final Object[] args) {
        // 1.先去实例缓存中获取单例对象
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }
        // 2.找不到则开始创建bean实例
        return createBean(beanName, getBeanDefinition(beanName), args);
    }


    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    /**
     * 添加beanPostProcessor
     *
     * @param beanPostProcessor bean
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }
    /* ============================抽象定义============================*/

    /**
     * 根据bean名称检索获取一个bean定义对象
     *
     * @param beanName bean名称
     * @return bean定义对象
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 根据传入的一个beanDefinition创建出一个bean
     *
     * @param beanName bean名称
     * @return 一个bean定义
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
