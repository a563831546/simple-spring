package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.PropertyValue;
import com.baogex.springframework.beans.PropertyValues;
import com.baogex.springframework.beans.factory.config.BeanReference;
import com.baogex.springframework.beans.factory.service.SimpleDao;
import com.baogex.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.service.SimpleService;
import com.baogex.springframework.beans.factory.support.SimpleInstantiationStrategy;
import com.baogex.springframework.beans.factory.support.xml.XmlBeanDefinitionReader;
import org.junit.Test;

public class ApiTest {

    @Test
    public void main() {
        String simpleDaoName = "simpleDao";
        String simpleServiceName = "simpleService";

        // 1.创建一个bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册daoBean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition("classpath:spring.xml");

        // 3.注册service实例,添加daoBean依赖
        SimpleService service = (SimpleService) beanFactory.getBean(simpleServiceName);
        System.out.println(service.getServiceName() + "---" + service.getUserName("2"));

    }

}