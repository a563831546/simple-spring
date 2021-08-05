package com.baogex.springframework.beans.factory.service;

import com.baogex.springframework.beans.factory.BeanNameAware;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class SimpleDao implements BeanNameAware {
    private final Map<String, String> userData = new HashMap<>(4);
    public final static String beanName = "<DAO>";

    public void initDataMethod() {
        System.out.println("[DAO]---initDataMethod");
        userData.put("1", "小明");
        userData.put("2", "中明");
        userData.put("3", "大明");
        userData.put("4", "究明");
    }

    public void destroyDataMethod() {
        System.out.println(beanName + "---destroyDataMethod");
        userData.clear();
    }

    public String getUserNameById(String id) {
        return userData.get(id);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(beanName + "--[Aware]-setBeanName:" + name);
    }
}
