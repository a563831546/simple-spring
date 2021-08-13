package com.baogex.springframework.context.support;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.config.BeanPostProcessor;
import com.baogex.springframework.context.ApplicationContext;
import com.baogex.springframework.context.ApplicationContextAware;

/**
 * @author : baogex.com
 * @since : 2021-08-05
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 在bean执行初始化前执行
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    /**
     * 在bean执行初始化后执行
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
