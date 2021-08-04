package com.baogex.springframework.beans.factory.support.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.PropertyValue;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.config.BeanReference;
import com.baogex.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.baogex.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.baogex.springframework.core.io.Resource;
import com.baogex.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

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
        } catch (IOException | ClassNotFoundException e) {
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


    private void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if ((!(childNodes.item(i) instanceof Element)) ||
                    !"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }
            Element bean = (Element) childNodes.item(i);
            // 标签解析
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            // bean在容器中名称,优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            beanName = StrUtil.lowerFirst(beanName);

            // bean不能重复
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException(" Duplicate beanName[" + beanName + "] is not allowed");
            }

            // bean的类信息
            Class<?> clazz = Class.forName(className);

            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                Node propertyNode = bean.getChildNodes().item(j);
                if ((!(propertyNode instanceof Element)) ||
                        !"property".equals(propertyNode.getNodeName())) {
                    continue;
                }
                Element property = (Element) propertyNode;
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            // 注册bean
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
