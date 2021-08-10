package com.baogex.springframework.beans.factory.config;

/**
 * 单例bean工厂
 *
 * @author : baogex.com
 * @since : 2021-08-02
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例Bean实例对象
     *
     * @param beanName 注册bean时名称
     * @return 已经注册的bean对象，如果没注册则返回null
     */
    Object getSingleton(String beanName);
}
