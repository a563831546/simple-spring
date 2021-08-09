package com.baogex.springframework.beans.factory.event;


import com.baogex.springframework.context.ApplicationListener;
import com.baogex.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
