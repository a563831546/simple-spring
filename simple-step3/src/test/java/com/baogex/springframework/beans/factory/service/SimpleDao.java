package com.baogex.springframework.beans.factory.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class SimpleDao {
    private Map<String, String> userData = new HashMap<>(4);

    {
        userData.put("1", "小明");
        userData.put("2", "中明");
        userData.put("3", "大明");
        userData.put("4", "究明");
    }

    public String getUserNameById(String id) {
        return userData.get(id);
    }
}
