package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.BeansException;

/**
 * <p>
 *
 * </p>
 *
 * @author : baogex.com
 * @date : 2021-08-02
 */
public interface InitializingBean {
    /**
     * 在bean填充完属性后设置
     */
    void afterPropertiesSet() throws Exception;
}
