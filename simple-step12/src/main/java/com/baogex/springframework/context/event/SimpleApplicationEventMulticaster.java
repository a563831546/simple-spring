package com.baogex.springframework.context.event;

import com.baogex.springframework.context.ApplicationEvent;
import com.baogex.springframework.context.ApplicationListener;

/**
 * @Author: baogex
 * @Date: 2021/8/9
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener applicationListener : getApplicationListeners(event)) {
            applicationListener.onApplicationEvent(event);
        }
    }
}
