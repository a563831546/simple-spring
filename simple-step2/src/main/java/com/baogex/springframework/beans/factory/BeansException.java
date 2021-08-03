package com.baogex.springframework.beans.factory;

/**
 * beans生命周期异常
 *
 * @author : baogex.com
 * @since : 2021-08-02
 */
public class BeansException extends RuntimeException {
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
