package com.baogex.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.PropertyValue;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.config.BeanReference;
import com.baogex.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.baogex.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.baogex.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import com.baogex.springframework.core.io.Resource;
import com.baogex.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinition(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinition(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void loadBeanDefinition(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinition(resource);
        }
    }

    @Override
    public void loadBeanDefinition(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinition(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinition(location);
        }
    }

    private void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        // 1.????????????????????????
        Element scanConfig = root.element("component-scan");
        if (scanConfig != null) {
            String basePackage = scanConfig.attributeValue("base-package");
            if (StrUtil.isEmpty(basePackage)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(basePackage);
        }

        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {

            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethodName = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");

            // bean????????????
            Class<?> clazz = Class.forName(className);
            // bean??????????????????,????????? id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            // bean????????????
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException(" Duplicate beanName[" + beanName + "] is not allowed");
            }


            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // ???????????????
            if (StrUtil.isNotEmpty(initMethod)) {
                beanDefinition.setInitMethodName(initMethod);
            }
            // ??????????????????
            if (StrUtil.isNotEmpty(destroyMethodName)) {
                beanDefinition.setDestroyMethodName(destroyMethodName);
            }

            // ?????????
            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            List<Element> propertyList = bean.elements("property");

            for (Element property : propertyList) {
                // ???????????????property
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                // ??????????????????????????????????????????
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // ??????????????????
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // ?????? BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void scanPackage(String basePackage) {
        String[] basePackages = StrUtil.splitToArray(basePackage, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
}
