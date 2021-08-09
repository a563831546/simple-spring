package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.factory.service.SimpleService;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author : baogex.com
 * @since : 2021-08-09
 */
public class AopTest {
//
//    @Test
//    public void test_proxy_method() {
//        Object service = new SimpleService();
//
//        Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), service.getClass(), new InvocationHandler() {
//            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* cn.bugstack.springframework.test.bean.IUserService.*(..))");
//
//            @Override
//            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
//                return null;
//            }
//        })
//    }
}
