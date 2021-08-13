package com.baogex.springframework.beans.factory;

import com.baogex.springframework.apo.AdvisedSupport;
import com.baogex.springframework.apo.MethodMatcher;
import com.baogex.springframework.apo.TargetSource;
import com.baogex.springframework.apo.aspectj.AspectJExpressionPointcut;
import com.baogex.springframework.apo.framework.Cglib2AopProxy;
import com.baogex.springframework.apo.framework.JdkDynamicAopProxy;
import com.baogex.springframework.apo.framework.ReflectiveMethodInvocation;
import com.baogex.springframework.beans.factory.interceptor.UserServiceInterceptor;
import com.baogex.springframework.beans.factory.service.IUserDao;
import com.baogex.springframework.beans.factory.service.IUserService;
import com.baogex.springframework.beans.factory.service.UserDao;
import com.baogex.springframework.beans.factory.service.UserService;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author : baogex.com
 * @since : 2021-08-09
 */
public class AopTest {

    @Test
    public void test_proxy_method() {
        Object realObject = new UserDao();

        IUserDao iUserDao = (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                realObject.getClass().getInterfaces(),
                new InvocationHandler() {
                    final MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.baogex.springframework.beans.factory.service.IUserDao.*(..))");

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                        // 目标函数是否匹配切面规则
                        if (methodMatcher.matches(method, realObject.getClass())) {
                            // 拦截器
                            MethodInterceptor interceptor = invocation -> {
                                long startTime = System.currentTimeMillis();
                                try {
                                    return invocation.proceed();
                                } finally {
                                    System.out.println("aop --- start");
                                    System.out.println("invoke method:" + method.getName());
                                    System.out.println("speed time:" + (System.currentTimeMillis() - startTime));
                                    System.out.println("aop --- end");
                                }
                            };
                            return interceptor.invoke(new ReflectiveMethodInvocation(realObject, method, arguments));
                        }
                        return method.invoke(realObject, arguments);
                    }
                });
        System.out.println("=======================start business=======================");
        System.out.println(iUserDao.queryUserName("2"));
        System.out.println("=======================end business=======================");
    }

    @Test
    public void test_pointCutMather() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.baogex.springframework.beans.factory.service.IUserService.*(..))");
        Class<IUserService> iUserServiceClass = IUserService.class;
        Method getUserNameById = iUserServiceClass.getDeclaredMethod("getUserNameById", String.class);

        System.out.println(pointcut.matches(IUserDao.class));
        System.out.println(pointcut.matches(getUserNameById, iUserServiceClass));
    }

    @Test
    public void test_dynamic() {
        IUserService userService = new UserService();

        String expression = "execution(* com.baogex.springframework.beans.factory.service.IUserService.*(..))";
        AdvisedSupport advisedSupport = new AdvisedSupport();
        // 设置匹配器
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut(expression));
        // 设置目标对象
        advisedSupport.setTargetSource(new TargetSource(userService));
        // 设置方法拦截器
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());

        // 获取jdk代理对象
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println(proxy_jdk.getUserNameById("1"));

        // 使用cglib代理
        IUserService cglibProxy = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println(cglibProxy.getUserNameById("2"));
    }
}
