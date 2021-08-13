package com.baogex.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.baogex.springframework.context.stereotype.Component;

import java.util.Set;

/**
 * @author : baogex.com
 * @since : 2021-08-13
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> beanDefinitions = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : beanDefinitions) {
                // 设置作用域
                String scope = resolveScopeAnnotation(beanDefinition);
                beanDefinition.setScope(scope);
                // 设置bean名称
                beanDefinitionRegistry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);

            }
        }


    }

    /**
     * 确定bean的名称
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component annotation = beanClass.getAnnotation(Component.class);
        String beanName = annotation.value();
        return StrUtil.isNotEmpty(beanName) ? beanName : StrUtil.lowerFirst(beanClass.getSimpleName());
    }

    /**
     * 解析作用域注解
     */
    private String resolveScopeAnnotation(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope annotation = beanClass.getAnnotation(Scope.class);
        String value = annotation.value();
        return value != null ? value : StrUtil.EMPTY;
    }
}
