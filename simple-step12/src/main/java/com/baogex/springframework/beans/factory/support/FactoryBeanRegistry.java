package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : baogex.com
 * @since : 2021-08-06
 */
public abstract class FactoryBeanRegistry extends DefaultSingletonBeanRegistry {
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>(16);


    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object beanObject = factoryBeanObjectCache.get(beanName);
        return beanObject != NULL_OBJECT ? beanObject : null;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        // 1.判断是否是单例
        if (factory.isSingleton()) {
            Object beanObject = factoryBeanObjectCache.get(beanName);
            if (beanObject == null) {
                beanObject = doGetObjectFromFactoryBean(factory, beanName);
                factoryBeanObjectCache.put(beanName, beanObject == null ? NULL_OBJECT : beanObject);
            }
            return (beanObject != NULL_OBJECT ? beanObject : null);
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName) {
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}