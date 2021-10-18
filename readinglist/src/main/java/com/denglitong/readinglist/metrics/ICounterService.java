package com.denglitong.readinglist.metrics;

/**
 * CounterService和GaugeService的实现Spring已经提供了
 * 只需要注入到所需的Bean，在适当的时候调用其中的方法，即可更新对应的metric值
 *
 * @author litong.deng@foxmail.com
 * @date 2021/10/18
 */
public interface ICounterService {
    void increment(String metricName);
    void decrement(String metricName);
    void reset(String metricName);
}
