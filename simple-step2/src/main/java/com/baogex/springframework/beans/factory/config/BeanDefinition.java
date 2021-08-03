package com.baogex.springframework.beans.factory.config;

/**
 * bean属性定义基类
 *
 * @author : baogex.com
 * @since : 2021-08-02
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class bean) {
        this.beanClass = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
