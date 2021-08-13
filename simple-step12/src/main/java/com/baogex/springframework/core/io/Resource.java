package com.baogex.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public interface Resource {
    /**
     * 获取的读取流对象
     *
     * @return 一个资源流对象
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
