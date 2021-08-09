package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.BeansException;

/**
 * <p>
 * 由 {@link BeanFactory} 中使用的对象实现的接口，这些对象本身就是工厂。
 * 如果一个 bean 实现了这个接口，它就被用作一个对象暴露的工厂，而不是直接作为一个将暴露自己的 bean 实例。
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-06
 */
public interface FactoryBean<T> {

    // 获取bean实例
    T getObject() throws Exception;

    // 获取bean类型
    Class<?> getObjectType();

    // 是否单例
    boolean isSingleton();
}
