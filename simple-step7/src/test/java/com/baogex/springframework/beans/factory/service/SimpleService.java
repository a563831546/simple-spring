package com.baogex.springframework.beans.factory.service;


import com.baogex.springframework.beans.factory.DisposableBean;
import com.baogex.springframework.beans.factory.InitializingBean;
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
public class SimpleService implements InitializingBean, DisposableBean {

    private String serviceName;

    private String uid;
    private String company;
    private String location;

    private SimpleDao dao;


    public String getUserNameById(String userId) {
        return dao.getUserNameById(userId);
    }

    /**
     * 在bean销毁时调用
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("[Service]---destroy");
    }

    /**
     * 在bean填充完属性后设置
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[Service]---afterPropertiesSet");
    }
}
