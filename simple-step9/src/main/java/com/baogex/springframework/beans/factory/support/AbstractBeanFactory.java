package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.FactoryBean;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.config.BeanPostProcessor;
import com.baogex.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.baogex.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象bean工厂，继承了DefaultSingletonBeanRegistry，
 *
 * @author : baogex.com
 * @since : 2021-08-02
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>(8);

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

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

    protected <T> T doGetBean(final String beanName, final Object[] args) {
        // 1.先去实例缓存中获取单例对象
        Object sharedInstance = getSingleton(beanName);
        if (sharedInstance != null) {
            return (T) getObjectForBeanInstance(sharedInstance, beanName);
        }
        // 2.找不到则开始创建bean实例
        Object bean = createBean(beanName, getBeanDefinition(beanName), args);
        return (T) getObjectForBeanInstance(bean, beanName);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        // 1.如果这个FactoryBean不做处理
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        // 2.先从缓存获取
        Object bean = getCachedObjectForFactoryBean(beanName);
        if (bean == null) {
            bean = getObjectFromFactoryBean((FactoryBean) beanInstance, beanName);
        }
        return bean;
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

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
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
