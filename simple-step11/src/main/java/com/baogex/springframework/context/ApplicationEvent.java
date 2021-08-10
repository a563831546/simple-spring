package com.baogex.springframework.context;

import java.util.EventObject;

/**
 * @Author: baogex
 * @Date: 2021/8/7
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
