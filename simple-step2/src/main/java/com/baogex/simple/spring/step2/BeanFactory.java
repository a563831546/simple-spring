package com.baogex.simple.spring.step2;

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
}
