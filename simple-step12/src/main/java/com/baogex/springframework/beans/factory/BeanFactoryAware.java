package com.baogex.springframework.beans.factory;

import com.baogex.springframework.beans.BeansException;

/**
 * 由希望知道自己拥有 {@link BeanFactory} 的 bean 实现的接口。
 *
 * @author : baogex.com
 * @since : 2021-08-05
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
