package com.baogex.springframework.apo.framework;

import com.baogex.springframework.apo.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : baogex.com
 * @since : 2021-08-10
 */
public class Cglib2AopProxy implements AopProxy {
    private final AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer proxy = new Enhancer();
        // 设置被代理类对象
        proxy.setSuperclass(advised.getTargetSource().getTarget().getClass());
        // 设置被代理接口
        proxy.setInterfaces(advised.getTargetSource().getTargetClass());
        // 设置回调接口
        proxy.setCallback(new DynamicAdvisedInterceptor(advised));
        return proxy.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        private final AdvisedSupport advised;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            // cglib函数调用调用器
            CglibMethodInvocation cglibMethodInvocation =
                    new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, objects, methodProxy);
            // 获取support中的3要素,判断是否匹配
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
                return advised.getMethodInterceptor().invoke(cglibMethodInvocation);
            }
            return cglibMethodInvocation.proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }

} 
