package com.baogex.springframework.core.io;

/**
 * <p>
 *
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-03
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 由入参指定一个资源位置,可以是classpath或者文件路径或者url路径
     *
     * @param location 资源位置
     * @return 一个资源对象
     */
    Resource getResource(String location);
}
