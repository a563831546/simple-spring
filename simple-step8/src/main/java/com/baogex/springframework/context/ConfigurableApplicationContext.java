package com.baogex.springframework.context;

import com.baogex.springframework.beans.BeansException;

/**
 * @author : baogex.com
 * @since : 2021-08-04
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器
     */
    void refresh() throws BeansException;

    /**
     * 向当前应用程序注册关闭钩子函数
     */
    void registerShutdownHook();

    /**
     * 关闭方法
     */
    void close();
}
