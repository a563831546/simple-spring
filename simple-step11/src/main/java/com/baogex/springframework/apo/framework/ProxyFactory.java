package com.baogex.springframework.apo.framework;

import com.baogex.springframework.apo.AdvisedSupport;

/**
 * @author : baogex.com
 * @since : 2021-08-11
 */
public class ProxyFactory {

    private final AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport support) {
        this.advisedSupport = support;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
