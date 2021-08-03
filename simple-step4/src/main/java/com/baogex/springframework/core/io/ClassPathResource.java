package com.baogex.springframework.core.io;


import cn.hutool.core.lang.Assert;
import com.baogex.springframework.util.ClassUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 类路径下资源对象
 *
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class ClassPathResource implements Resource {
    /**
     * 资源路径
     */
    private final String path;
    /**
     * 类加载器
     */
    private final ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "path must not null");
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtil.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(this.path);
        if (inputStream == null) {
            throw new FileNotFoundException(this.path + " cannot  be opened because is does not exist ");
        }
        return inputStream;
    }
}
