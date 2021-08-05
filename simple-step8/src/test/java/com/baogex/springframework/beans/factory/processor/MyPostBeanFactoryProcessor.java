package com.baogex.springframework.beans.factory.processor;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.PropertyValue;
import com.baogex.springframework.beans.factory.ApiTest;
import com.baogex.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author : baogex.com
 * @since : 2021-08-04
 */
public class MyPostBeanFactoryProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition(ApiTest.simpleServiceName);
        beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue("company", "啊八八八八"));
    }
}
