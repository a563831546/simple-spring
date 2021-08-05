package com.baogex.springframework.context.support;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.baogex.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.baogex.springframework.beans.factory.config.BeanPostProcessor;
import com.baogex.springframework.context.ConfigurableApplicationContext;
import com.baogex.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author : baogex.com
 * @since : 2021-08-04
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * 刷新容器
     */
    @Override
    public void refresh() throws BeansException {
        // 1.创建工厂，并加载BeanDefinition 
        refreshBeanFactory();

        // 2.获取工厂
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3.在 Bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4.BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5.提前实例单例bean对象
        beanFactory.preInstantiateSingletons();
    }

    /**
     * BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor postProcessor : beansOfType.values()) {
            beanFactory.addBeanPostProcessor(postProcessor);
        }

    }

    /**
     * 在 Bean 实例化之前，执行 BeanFactoryPostProcessor
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor postProcessor : beansOfType.values()) {
            postProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /* 获取工厂 */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /* 创建工厂，加载BeanDefinition */
    protected abstract void refreshBeanFactory();

    /**
     * 向当前应用程序注册关闭钩子函数
     */
    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    /**
     * 关闭方法
     */
    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }

    /**
     * 按类型获取bean集合
     *
     * @param type
     * @return
     * @throws BeansException
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }
}
