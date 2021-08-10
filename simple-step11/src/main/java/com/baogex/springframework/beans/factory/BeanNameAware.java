package com.baogex.springframework.beans.factory;

/**
 * 由想要在 bean 工厂中知道其 bean 名称的 bean 实现的接口。
 *
 * @author : baogex.com
 * @since : 2021-08-05
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);
}
