package com.baogex.springframework.beans.factory.service;

/**
 * @author : baogex.com
 * @since : 2021-08-02
 */
public class SimpleService {

    private String serviceName;

    private SimpleDao dao;

    public String getServiceName() {
        return this.serviceName;
    }

    public String getUserName(String userId) {
        return dao.getUserNameById(userId);
    }
}
