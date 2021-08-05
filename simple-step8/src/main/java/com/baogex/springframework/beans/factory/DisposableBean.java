package com.baogex.springframework.beans.factory;

/**
 * <p>
 *
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-05
 */
public interface DisposableBean {

    /**
     * 在bean销毁时调用
     * @throws Exception
     */
    void destroy() throws Exception;
}
