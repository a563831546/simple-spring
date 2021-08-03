package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.PropertyValue;
import com.baogex.springframework.beans.PropertyValues;
import com.baogex.springframework.beans.factory.config.BeanReference;
import com.baogex.springframework.beans.factory.service.SimpleDao;
import com.baogex.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.service.SimpleService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void main() {
        String simpleDaoName = "simpleDao";
        String simpleServiceName = "simpleService";

        // 1.创建一个bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册daoBean
        beanFactory.registerBeanDefinition(simpleDaoName, new BeanDefinition(SimpleDao.class, null));

        // 3.注册service实例,添加daoBean依赖
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("dao", new BeanReference(simpleDaoName)));
        propertyValues.addPropertyValue(new PropertyValue("serviceName", "testServiceName"));
        beanFactory.registerBeanDefinition(simpleServiceName, new BeanDefinition(SimpleService.class, propertyValues));

        // 4.获取实例
        SimpleService service = (SimpleService) beanFactory.getBean(simpleServiceName);

        // 5.执行方法
        System.out.println(service.getServiceName() + ":" + service.getUserName("3"));

    }

}