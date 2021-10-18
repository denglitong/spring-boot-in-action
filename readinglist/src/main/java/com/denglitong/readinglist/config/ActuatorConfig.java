package com.denglitong.readinglist.config;

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/18
 */
@Configuration
public class ActuatorConfig {

    /**
     * trace 内存条目默认100，超过会按LRU置换
     * 这里设置自定义容量为1000
     *
     * @return
     */
    @Bean
    public InMemoryHttpTraceRepository traceRepository() {
        InMemoryHttpTraceRepository traceRepo = new InMemoryHttpTraceRepository();
        traceRepo.setCapacity(1000);
        return traceRepo;
    }
}
