package com.baogex.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.baogex.springframework.beans.factory.config.BeanDefinition;
import com.baogex.springframework.context.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author : baogex.com
 * @since : 2021-08-13
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {

        Set<BeanDefinition> result = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            result.add(new BeanDefinition(clazz));
        }
        return result;
    }
}
