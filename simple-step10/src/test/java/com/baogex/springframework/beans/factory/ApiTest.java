package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.factory.event.CustomEvent;
import com.baogex.springframework.beans.factory.processor.MyPostBeanFactoryProcessor;
import com.baogex.springframework.beans.factory.processor.MyPostBeanProcessor;
import com.baogex.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.baogex.springframework.beans.factory.service.UserService;
import com.baogex.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.baogex.springframework.context.ApplicationContext;
import com.baogex.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class ApiTest {
    public static String simpleDaoName = "simpleDao";
    public static String simpleServiceName = "simpleService";

    @Test
    public void main() {
        // 1.创建一个bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册daoBean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyPostBeanFactoryProcessor myPostBeanFactoryProcessor = new MyPostBeanFactoryProcessor();
        myPostBeanFactoryProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyPostBeanProcessor myPostBeanProcessor = new MyPostBeanProcessor();
        beanFactory.addBeanPostProcessor(myPostBeanProcessor);

        // 3.注册service实例,添加daoBean依赖
        UserService service = beanFactory.getBean(simpleServiceName, UserService.class);

        System.out.println(service.getServiceName() + "---" + service.getUserNameById("2"));
        System.out.println(service);
    }

    @Test
    public void test_prototype() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService service1 = applicationContext.getBean(simpleServiceName, UserService.class);
        UserService service2 = applicationContext.getBean(simpleServiceName, UserService.class);
        System.out.println(service1);
        System.out.println(service2);
        // 4. 打印十六进制哈希
        System.out.println(service1 + " 十六进制哈希：" + Integer.toHexString(service1.hashCode()));
        System.out.println(ClassLayout.parseInstance(service1).toPrintable());
    }

    @Test
    public void testXml() {
        System.out.println("=====step1======[ApiTest]---new ClassPathXmlApplicationContext===========");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        System.out.println("=====step2======[ApiTest]---call registerShutdownHook===========");
        applicationContext.registerShutdownHook();

        System.out.println("=====step3======[ApiTest]---applicationContext.getBean===========");
        UserService simpleService = applicationContext.getBean(simpleServiceName, UserService.class);
        System.out.println("-----------------Start Business processing-----------------");
        System.out.println(simpleService.getUserNameById("3"));
        System.out.println("-----------------Stop  Business processing-----------------");
    }

    @Test
    public void testEvent() {
        System.out.println("=====step1======[ApiTest]---new ClassPathXmlApplicationContext===========");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        applicationContext.publishEvent(new CustomEvent(applicationContext, 1L, "哈哈哈"));

        System.out.println("=====step2======[ApiTest]---call registerShutdownHook===========");
        applicationContext.registerShutdownHook();
    }

}