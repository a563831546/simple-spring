package com.baogex.springframework.context.event;

import com.baogex.springframework.context.ApplicationEvent;

/**
 * @Author: baogex
 * @Date: 2021/8/7
 */
public class ContextRefreshedEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
