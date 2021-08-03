package com.baogex.springframework.beans.step1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * bean工厂
 * </p>
 *
 * @author : baogex.com
 * @date : 2021-08-02
 */
public class BeanFactory {

    private final Map<String, BeanDefinition> context = new ConcurrentHashMap<>(8);

    public Object getBean(String name) {
        return context.get(name);
    }

    public <T> T getBean(String name, Class<T> clazz) {
        BeanDefinition beanDefinition = context.get(name);
        return beanDefinition != null ? (T) beanDefinition.getBean() : null;
    }

    public void registerBeanDefinition(String name, BeanDefinition bean) {
        context.put(name, bean);
    }

    public void registerBeanDefinition(BeanDefinition bean) {
        if (bean == null || bean.getBean() == null) return;
        String beanName = castDownFirst(bean.getBean().getClass().getSimpleName());
        context.put(beanName, bean);
    }

    public String castDownFirst(String dealData) {
        char[] chars = dealData.toCharArray();
        chars[0] = (char) ((chars[0] >= 65 && chars[0] <= 90) ? (chars[0] + 32) : chars[0]);
        return new String(chars);
    }
}
