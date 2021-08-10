package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.BeansException;

/**
 * <p>
 *
 * </p>
 *
 * @author : baogex.com
 * @date : 2021-08-02
 */
public interface BeanFactory {

    /**
     * 获取一个bean实例对象
     *
     * @param beanName bean名称（id)
     * @return 返回一个bean实例对象
     */
    Object getBean(String beanName);


    /**
     * 根据入参列表指定构造函数获取bean
     *
     * @param beanName 名称
     * @param args     参数列表
     * @return 返回一个创建好的bean实例对象，如果不存在则返回null
     */
    Object getBean(String beanName, Object... args);

    /**
     * 根绝类
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
