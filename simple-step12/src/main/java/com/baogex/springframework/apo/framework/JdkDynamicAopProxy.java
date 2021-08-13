package com.baogex.springframework.apo.framework;

import com.baogex.springframework.apo.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : baogex.com
 * @since : 2021-08-10
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    // 增强支持封装参数
    private AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                advisedSupport.getTargetSource().getTargetClass(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取被代理目标对象
        Object target = this.advisedSupport.getTargetSource().getTarget();
        // 函数是否与生产的切面匹配器
        if (advisedSupport.getMethodMatcher().matches(method, target.getClass())) {
            MethodInterceptor interceptor = advisedSupport.getMethodInterceptor();
            return interceptor.invoke(new ReflectiveMethodInvocation(target, method, args));
        }
        return method.invoke(target, args);
    }

    public AdvisedSupport getAdvisedSupport() {
        return advisedSupport;
    }

    public void setAdvisedSupport(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }
}
