package com.baogex.springframework.beans.factory.service;


import com.baogex.springframework.beans.BeansException;
import com.baogex.springframework.beans.factory.*;
import com.baogex.springframework.context.ApplicationContext;
import com.baogex.springframework.context.ApplicationContextAware;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : baogex.com
 * @since : 2021-08-02
 */
@Getter
@Setter
public class UserService implements IUserService, InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
    public final static String beanName = "<Service>";
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    private String serviceName;
    private String uid;
    private String company;
    private String location;
    private IUserDao dao;

    public UserService() {
        dao = new UserDao();
    }

    @Override
    public String getUserNameById(String userId) {
        System.out.println("=======dao：" + dao);
        return dao.queryUserName(userId);
    }

    /**
     * 在bean销毁时调用
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println(beanName + "---destroy");
    }

    /**
     * 在bean填充完属性后设置
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(beanName + "---afterPropertiesSet");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println(beanName + "--[Aware]-setBeanClassLoader:" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(beanName + "--[Aware]-setBeanFactory:" + beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(beanName + "--[Aware]-setBeanName:" + name);

    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        System.out.println(beanName + "--[Aware]-setApplicationContext:" + context);
        this.applicationContext = context;
    }
}
