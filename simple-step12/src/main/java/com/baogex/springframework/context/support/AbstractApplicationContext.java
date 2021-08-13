package com.baogex.springframework.context.support;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.baogex.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.baogex.springframework.beans.factory.config.BeanPostProcessor;
import com.baogex.springframework.context.ApplicationEvent;
import com.baogex.springframework.context.ApplicationListener;
import com.baogex.springframework.context.ConfigurableApplicationContext;
import com.baogex.springframework.context.event.ApplicationEventMulticaster;
import com.baogex.springframework.context.event.ContextClosedEvent;
import com.baogex.springframework.context.event.ContextRefreshedEvent;
import com.baogex.springframework.context.event.SimpleApplicationEventMulticaster;
import com.baogex.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author : baogex.com
 * @since : 2021-08-04
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    // 应用程序事件多播 Bean 名称
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    /**
     * 刷新容器
     */
    @Override
    public void refresh() throws BeansException {
        // 1.创建工厂，并加载BeanDefinition 
        refreshBeanFactory();
    
        // 2.获取工厂
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3.添加 ApplicationContextAware 感知
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4.在 Bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5.BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 6.初始化事件发布者
        initApplicationEventMulticaster();

        // 7.注册事件监听器
        registerListeners();

        // 8.提前实例单例bean对象
        beanFactory.preInstantiateSingletons();

        // 9.发布刷新完成事件
        finishRefresh();
    }

    /**
     * 初始化事件监听器
     */
    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, beanFactory);
    }

    /**
     * 注册事件发布者
     */
    private void registerListeners() {
        for (ApplicationListener listener : getBeansOfType(ApplicationListener.class).values()) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }


    /**
     * 发布刷新完成事件
     */
    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
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
        // 1.发布关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 2.执行销毁单例bean的销毁方法
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
