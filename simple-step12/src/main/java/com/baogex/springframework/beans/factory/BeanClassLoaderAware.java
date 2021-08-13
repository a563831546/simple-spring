package com.baogex.springframework.beans.factory;

/**
 * <p>
 * 允许 bean 知道 bean 的回调{@link ClassLoader 类加载器}；即当前 bean 工厂用来加载 bean 类的类加载器。
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-05
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
