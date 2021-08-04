package com.baogex.springframework.beans.factory.config;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.BeanFactory;

/**
 * <p>
 * BeanFactory接口的扩展将由能够自动装配的 bean 工厂实现，
 * 前提是它们希望为现有 bean 实例公开此功能。
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-04
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName) throws BeansException;
    
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;
}
