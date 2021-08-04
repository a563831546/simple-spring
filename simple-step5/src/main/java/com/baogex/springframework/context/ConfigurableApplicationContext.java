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
}
