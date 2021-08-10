package com.baogex.springframework.beans.factory.config;

import com.baogex.springframework.beans.PropertyValues;
import lombok.Getter;
import lombok.Setter;

/**
 * bean属性定义基类
 *
 * @author : baogex.com
 * @since : 2021-08-02
 */
@Getter
@Setter
public class BeanDefinition {
    private Class<?> beanClass;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
    }
}
