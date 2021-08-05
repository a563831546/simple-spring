package com.baogex.springframework.context.support;

import com.baogex.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.baogex.springframework.beans.factory.support.xml.XmlBeanDefinitionReader;

/**
 * @author : baogex.com
 * @since : 2021-08-04
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}
