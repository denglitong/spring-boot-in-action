package com.denglitong.readinglist.config;

import com.denglitong.readinglist.ReadinglistApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/19
 */
public class ReadingListServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指定Spring配置，ReadinglistApplication 既是启动类，也是配置类
        return builder.sources(ReadinglistApplication.class);
    }
}
