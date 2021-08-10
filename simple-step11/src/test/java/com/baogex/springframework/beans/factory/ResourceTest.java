package com.baogex.springframework.beans.factory;

import cn.hutool.core.io.IoUtil;
import com.baogex.springframework.core.io.DefaultResourceLoader;
import com.baogex.springframework.core.io.Resource;
import com.baogex.springframework.core.io.ResourceLoader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class ResourceTest {
    private ResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testClassPath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        String readContent = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(readContent);
    }

    @Test
    public void testFilePath() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        String readContent = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(readContent);
    }

    @Test
    public void testURL() throws IOException {
        Resource resource = resourceLoader.getResource("https://www.baidu.com");
        String readContent = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(readContent);
    }

}
