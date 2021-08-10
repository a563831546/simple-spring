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

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    /* bean的类对象信息 */
    private Class<?> beanClass;

    /* 属性值对 */
    private PropertyValues propertyValues;

    /* 作用域，默认：单例对象 */
    private String scope = SCOPE_SINGLETON;

    /* 是否单例，默认：true */
    private boolean singleton = true;

    /* 是否原型，默认：false */
    private boolean prototype = false;

    /* 初始化函数名称 */
    private String initMethodName;

    /* 销毁函数名称 */
    private String destroyMethodName;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
    }

    public void setScope(String scope) {
        this.scope = scope;
        singleton = SCOPE_SINGLETON.equals(scope);
        prototype = SCOPE_PROTOTYPE.equals(scope);
    }
}
