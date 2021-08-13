package com.baogex.springframework.beans.factory.service;

import com.baogex.springframework.context.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : baogex.com
 * @since : 2021-08-13
 */
@Setter
@Getter
@Component("userService")
public class SuperUserService implements IUserService {

    private String token;

    private IUserDao userDao;

    @Override
    public String getUserNameById(String userId) {
        return userDao.queryUserName("2");
    }

    @Override
    public String toString() {
        return "SuperUserService{" +
                "token='" + token + '\'' +
                ", userDao=" + userDao +
                '}';
    }
}
