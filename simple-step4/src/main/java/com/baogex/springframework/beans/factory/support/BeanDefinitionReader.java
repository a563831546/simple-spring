package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.core.io.Resource;
import com.baogex.springframework.core.io.ResourceLoader;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public interface BeanDefinitionReader {
    /**
     * 获取一个支持bean注册实例对象
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取一个资源加载器
     */
    ResourceLoader getResourceLoader();

    /**
     * 把resource资源解析后加载至容器
     */
    void loadBeanDefinition(Resource resource) throws BeansException;

    /**
     * 把resource资源解析后加载至容器
     */
    void loadBeanDefinition(Resource... resource) throws BeansException;

    /**
     * 把resource资源解析后加载至容器
     */
    void loadBeanDefinition(String location) throws BeansException;
}
