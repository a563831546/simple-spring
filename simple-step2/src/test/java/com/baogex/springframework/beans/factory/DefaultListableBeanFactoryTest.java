package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.service.SimpleService;
import com.baogex.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class DefaultListableBeanFactoryTest {

    @Test
    public void main() {
        String simpleServiceName = "simpleService";

        // 1.创建一个bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册一个bean实例到工厂
        beanFactory.registerBeanDefinition(simpleServiceName, new BeanDefinition(SimpleService.class));

        // 3.第一次获取实例
        ((SimpleService) beanFactory.getBean(simpleServiceName)).doSomething();

        // 4.测试第二次获取实例
        ((SimpleService) beanFactory.getBean(simpleServiceName)).doSomething();
    }

}