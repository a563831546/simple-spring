package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.PropertyValue;
import com.baogex.springframework.beans.PropertyValues;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.baogex.springframework.core.io.DefaultResourceLoader;
import com.baogex.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author : baogex.com
 * @since : 2021-08-13
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * 占位符解析式前缀
     */
    private static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    /**
     * 占位符解析式后缀
     */
    private static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    /**
     * properties文件资源路径
     */
    private String location;

    /**
     * 加载beanDefinition之后，在实例化bean对象前，支持修改beanDefinition属性机制
     *
     * @param beanFactory bean工厂
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            // 1.记载资源文件，属性配置文件properties
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            // 2.获取bean定义
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (value instanceof String) {
                        String str = (String) value;
                        StringBuilder buffer = new StringBuilder(str);
                        int startPlaceholderIndex = str.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                        int endPlaceholderIndex = str.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                        if (startPlaceholderIndex != -1 && endPlaceholderIndex != -1 && startPlaceholderIndex < endPlaceholderIndex) {
                            String mappingValue = str.substring(startPlaceholderIndex + 2, endPlaceholderIndex);
                            String propertiesValue = properties.getProperty(mappingValue);
                            buffer.replace(startPlaceholderIndex, endPlaceholderIndex + 1, propertiesValue);
                            propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new BeansException("fail to load resource:" + location);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
