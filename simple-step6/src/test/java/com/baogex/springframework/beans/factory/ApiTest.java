package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.factory.processor.MyPostBeanFactoryProcessor;
import com.baogex.springframework.beans.factory.processor.MyPostBeanProcessor;
import com.baogex.springframework.beans.factory.service.SimpleService;
import com.baogex.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.baogex.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.baogex.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

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
        SimpleService service = beanFactory.getBean(simpleServiceName, SimpleService.class);

        System.out.println(service.getServiceName() + "---" + service.getUserNameById("2"));
        System.out.println(service);
    }

    @Test
    public void testXml() {
        System.out.println("[ApiTest]---new ClassPathXmlApplicationContext");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        System.out.println("[ApiTest]---call registerShutdownHook");
        applicationContext.registerShutdownHook();

        System.out.println("[ApiTest]---applicationContext.getBean");
        SimpleService simpleService = applicationContext.getBean(simpleServiceName, SimpleService.class);
        System.out.println("-----------------Start Business processing-----------------");
        System.out.println(simpleService.getUserNameById("3"));
        System.out.println("-----------------Stop  Business processing-----------------");
    }
}