package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.BeansException;

import java.util.Map;

/**
 * <p>
 * BeanFactory接口的扩展，由可以枚举其所有 bean 实例的 bean 工厂实现，
 * 而不是按照客户的要求逐一尝试通过名称查找 bean。
 * 预加载所有 bean 定义的 BeanFactory 实现（例如基于 XML 的工厂）可以实现此接口
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-04
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按类型获取bean集合
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 获取所有bean名称集合
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
