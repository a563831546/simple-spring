package com.baogex.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.DisposableBean;
import com.baogex.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author : baogex.com
 * @since : 2021-08-05
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final String beanName;
    private final Object bean;
    private final String destroyMethodName;

    public DisposableBeanAdapter(String beanName, Object bean, BeanDefinition beanDefinition) {
        this.beanName = beanName;
        this.bean = bean;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    /**
     * 销毁操作，接口 > destroy-method
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        // 1.执行销毁接口
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        // 2.执行destroy-method,{判断是为了避免二次执行销毁},上面已经执行过destroy()
        if (StrUtil.isNotEmpty(destroyMethodName)) {
            if (!(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))) {
                Method declaredMethod = bean.getClass().getMethod(destroyMethodName);
                declaredMethod.invoke(bean);
            }
        }
    }
}
