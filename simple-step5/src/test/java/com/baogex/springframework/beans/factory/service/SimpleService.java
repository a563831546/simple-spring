package com.baogex.springframework.beans.factory.service;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : baogex.com
 * @since : 2021-08-02
 */
@Getter
@Setter
@ToString
public class SimpleService {

    private String serviceName;
    
    private String  uid;
    private String company;
    private String location;

    private SimpleDao dao;


    public String getUserNameById(String userId) {
        return dao.getUserNameById(userId);
    }
}
