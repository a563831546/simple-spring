package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认单例Bean注册实现
 *
 * @author : baogex.com
 * @since : 2021-08-02
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> registers = new ConcurrentHashMap<>(8);

    /**
     * 获取一个单例bean，根据bean名称取检索
     *
     * @param beanName 注册bean时名称
     * @return
     */
    @Override
    public Object getSingleton(String beanName) {
        return registers.get(beanName);
    }

    /**
     * 添加一个bean实例到注册容器中，key为bean名称，value为bean实例对象
     *
     * @param beanName        bean名称
     * @param singletonObject bean的实例对象
     */
    protected void addSingleton(String beanName, Object args) {
        registers.put(beanName, args);
    }
}
