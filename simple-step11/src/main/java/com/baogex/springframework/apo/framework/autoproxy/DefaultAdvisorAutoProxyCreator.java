package com.baogex.springframework.apo.framework.autoproxy;

import com.baogex.springframework.apo.*;
import com.baogex.springframework.apo.aspectj.AspectJExpressionPointcutAdvisor;
import com.baogex.springframework.apo.framework.ProxyFactory;
import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.BeanFactory;
import com.baogex.springframework.beans.factory.BeanFactoryAware;
import com.baogex.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.baogex.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * @author : baogex.com
 * @since : 2021-08-11
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // 
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        Collection<AspectJExpressionPointcutAdvisor> values = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : values) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) continue;
            AdvisedSupport advisedSupport = new AdvisedSupport();

            try {
                advisedSupport.setTargetSource(new TargetSource(beanClass.getDeclaredConstructor().newInstance()));
            } catch (Exception ignore) {
            }
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvisor());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);
            
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    /**
     * ?????????????????????
     *
     * @param beanClass
     * @return
     */
    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) ||
                Advisor.class.isAssignableFrom(beanClass) ||
                Pointcut.class.isAssignableFrom(beanClass);
    }

    /**
     * ???bean????????????????????????
     *
     * @param bean     ??????bean??????
     * @param beanName bean??????
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * ???bean????????????????????????
     *
     * @param bean     ??????bean??????
     * @param beanName bean??????
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
