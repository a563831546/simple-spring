package com.baogex.springframework.context;

/**
 * @Author: baogex
 * @Date: 2021/8/9
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
