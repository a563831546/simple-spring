package com.baogex.springframework.context.event;

import com.baogex.springframework.context.ApplicationEvent;
import com.baogex.springframework.context.ApplicationListener;

/**
 * @Author: baogex
 * @Date: 2021/8/7
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);


    void removeApplicationListener(ApplicationListener<?> listener);


    void multicastEvent(ApplicationEvent event);

}
