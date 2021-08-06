package com.baogex.springframework.beans.factory.service;

import com.baogex.springframework.beans.factory.BeanNameAware;
import com.baogex.springframework.beans.factory.FactoryBean;
import com.baogex.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao>, InitializingBean {
    public final static String beanName = "<DAO>";
    Map<String, String> userData = new HashMap<>(4);

    /**
     * 在bean填充完属性后设置
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(beanName + "--[InitializingBean]-afterPropertiesSet");
        userData.put("1", "小明");
        userData.put("2", "中明");
        userData.put("3", "大明");
        userData.put("4", "究明");
    }

    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            System.out.println("ProxyMethod--" + method.getName());

            if ("queryUserName".equals(method.getName())) {
                return userData.get(args[0].toString());
            } else {
                return method.invoke(this, args);
            }
        };

        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }


}
