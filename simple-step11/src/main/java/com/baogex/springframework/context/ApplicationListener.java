package com.baogex.springframework.context;

import java.util.EventListener;

/**
 * 应用程序事件侦听器要实现的接口。
 * 基于 Observer 设计模式的标准 <code>java.util.EventListener<code> 接口。
 *
 * @Author: baogex
 * @Date: 2021/8/7
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
