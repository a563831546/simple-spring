package com.baogex.simple.spring.step2.support;

import com.baogex.simple.spring.step2.BeansException;
import com.baogex.simple.spring.step2.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : baogex.com
 * @since : 2021-08-02
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(8);

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (null == beanDefinition) {
            throw new BeansException("no bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    /**
     * 注册一个bean定义到缓存中取
     *
     * @param beanName       bean名称、id
     * @param beanDefinition bean定义实例对象
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
