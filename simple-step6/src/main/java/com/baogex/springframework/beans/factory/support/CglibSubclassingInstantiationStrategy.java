package com.baogex.springframework.beans.factory.support;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * Cglib创建实例对象
 *
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    /**
     * 实例化一个对象
     *
     * @param beanDefinition bean定义信息
     * @param beanName       bean名称
     * @param ctor           构造函数信息
     * @param args           参数
     * @return 返回一个创建好的实例对象
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args) throws BeansException {
        if (beanDefinition == null || beanDefinition.getBeanClass() == null) {
            return null;
        }

        // 1.创建cglib代理类
        Enhancer enhancer = new Enhancer();
        // 2.设置代理实例对象
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        // 3.如果传入指定构造器,则按构造器创建实例
        return ctor == null ? enhancer.create() : enhancer.create(ctor.getExceptionTypes(), args);
    }
}
