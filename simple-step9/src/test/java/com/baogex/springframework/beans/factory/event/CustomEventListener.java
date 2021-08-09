package com.baogex.springframework.beans.factory.event;

import com.baogex.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author : baogex.com
 * @since : 2021-08-09
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
