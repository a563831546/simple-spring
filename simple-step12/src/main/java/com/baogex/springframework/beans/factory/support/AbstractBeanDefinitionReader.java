package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.core.io.DefaultResourceLoader;
import com.baogex.springframework.core.io.ResourceLoader;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;

    private final ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    /**
     * 获取一个支持bean注册实例对象
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    /**
     * 获取一个资源加载器
     */
    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
