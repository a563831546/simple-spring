package com.baogex.springframework.beans.factory.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class UserDao implements IUserDao {
    public final static String beanName = "<DAO>";
    private final Map<String, String> userData = new HashMap<>(4);

    {
        userData.put("1", "小明");
        userData.put("2", "中明");
        userData.put("3", "大明");
        userData.put("4", "究明");
    }

    public String queryUserName(String id) {
        return userData.get(id);
    }
}
