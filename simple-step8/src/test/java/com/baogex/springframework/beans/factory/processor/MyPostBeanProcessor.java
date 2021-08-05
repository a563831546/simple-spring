package com.baogex.springframework.beans.factory.processor;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.ApiTest;
import com.baogex.springframework.beans.factory.config.BeanPostProcessor;
import com.baogex.springframework.beans.factory.service.SimpleService;

/**
 * @author : baogex.com
 * @since : 2021-08-04
 */
public class MyPostBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ApiTest.simpleServiceName.equals(beanName)) {
            SimpleService simpleService = (SimpleService) bean;
            simpleService.setLocation("南京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
