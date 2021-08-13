package com.baogex.springframework.context;

import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.Aware;

/**
 * 由任何希望被通知它运行的 {@link ApplicationContext} 的对象实现的接口。
 *
 * @author : baogex.com
 * @since : 2021-08-05
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext context) throws BeansException;

}
