package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.factory.service.IUserService;
import com.baogex.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author : baogex.com
 * @since : 2021-08-13
 */
public class ContextTest {

    @Test
    public void test_property() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("superUser", IUserService.class);
        System.out.println("测试结果：" + userService);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

    }


}
