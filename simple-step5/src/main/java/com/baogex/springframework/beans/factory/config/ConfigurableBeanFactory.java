package com.baogex.springframework.beans.factory.config;

import com.baogex.springframework.beans.factory.BeanFactory;
import com.baogex.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 大多数 bean 工厂要实现的配置接口。
 * 除了 {@link BeanFactory} 接口中的 bean factory client 方法之外，还提供配置 bean factory 的工具。
 *
 * @author : baogex.com
 * @since : 2021-08-04
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
