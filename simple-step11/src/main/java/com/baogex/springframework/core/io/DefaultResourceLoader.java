package com.baogex.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class DefaultResourceLoader implements ResourceLoader {
    /**
     * 如果资源路径是以 classpath: 开头则按{@link ClassPathResource} 加载资源,
     * 否则按url尝试加载,加载成功则按{@link UrlResource}加载,否则按{@link FileSystemResource}
     *
     * @param location 资源位置
     * @return
     */
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
