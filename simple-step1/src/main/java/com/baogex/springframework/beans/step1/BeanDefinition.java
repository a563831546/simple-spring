package com.baogex.springframework.beans.step1;

/**
 * <p>
 *
 * </p>
 *
 * @author : baogex.com
 * @date : 2021-08-02
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object object) {
        this.bean = object;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
