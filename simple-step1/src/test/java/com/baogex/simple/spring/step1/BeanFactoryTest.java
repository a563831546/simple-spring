package com.baogex.simple.spring.step1;

import com.baogex.simple.spring.step1.service.TestService;
import org.junit.Test;


public class BeanFactoryTest {


    @Test
    public void test1() {
        // 1.创建容器
        BeanFactory beanFactory = new BeanFactory();

        // 2.注册beanDefinition到容器
        beanFactory.registerBeanDefinition( new BeanDefinition(new TestService()));

        // 3.获取bean
        TestService testService = beanFactory.getBean("testService", TestService.class);

        // 4.执行业务代码
        testService.doSomething();
    }


}