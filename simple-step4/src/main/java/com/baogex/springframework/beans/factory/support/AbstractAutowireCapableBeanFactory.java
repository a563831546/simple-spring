package com.baogex.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.PropertyValue;
import com.baogex.springframework.beans.PropertyValues;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.config.BeanReference;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author : baogex.com
 * @since : 2021-08-02
 */
@Getter
@Setter
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    /**
     * 策略对象,提供创建的实例的具体策略,如cglib &  jdk
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建bean实例
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     * @param args           入参
     * @return 返回一个bean实例对象
     * @throws BeansException 创建bean发生的异常
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        try {
            // 1.创建bean实例
            Object bean = createBeanInstance(beanName, beanDefinition, args);
            // 2.填充属性值
            applyPropertyValues(beanName, bean, beanDefinition);
            // 3.添加至容器
            addSingleton(beanName, bean);
            return bean;
        } catch (Exception e) {
            throw new BeansException("实例化Bean时发生错误", e);
        }
    }

    /**
     * 为bean添加属性值
     *
     * @param beanName       bean名词
     * @param bean           bean实例对象
     * @param beanDefinition bean定义信息
     */
    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1.获取propertyValues属性集
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            // 2.遍历属性集合
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String fieldName = propertyValue.getName();
                Object value = propertyValue.getValue();
                // 2.1 如果属性值对象是bean对象, 则将该bean注入至当前 bean
                if (value instanceof BeanReference) {
                    String referenceBeanName = ((BeanReference) value).getBeanName();
                    value = getBean(referenceBeanName);
                }
                // 2.2 属性填充
                BeanUtil.setFieldValue(bean, fieldName, value);
            }
        } catch (Exception e) {
            throw new BeansException("Bean: " + beanName + " Property fill error", e);
        }

    }

    /**
     * 带有入参的创建实例对象
     *
     * @param beanDefinition bean定义属性
     * @param beanName       bean名称
     * @param args           入参
     * @return 返回一个创建好的实例, 如果不存在返回null
     */
    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor<?> ctor = null;
        // 1.基本校验
        if (beanDefinition == null || beanDefinition.getBeanClass() == null) {
            return null;
        }
        // 2.循环遍历构造函数, 暂时已长度来简单匹配,后续改进
        if (args != null) {
            Class<?> beanClass = beanDefinition.getBeanClass();
            for (Constructor<?> constructor : beanClass.getConstructors()) {
                if (constructor.getParameterCount() == args.length) {
                    ctor = constructor;
                }
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, ctor, args);
    }

}
