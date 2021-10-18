package com.denglitong.readinglist.metrics;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/18
 */
public interface IGaugeService {
    void submit(String metricName, double value);
}
