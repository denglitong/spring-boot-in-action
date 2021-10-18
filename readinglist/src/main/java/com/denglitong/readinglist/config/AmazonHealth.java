package com.denglitong.readinglist.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义metrics health
 *
 * @author litong.deng@foxmail.com
 * @date 2021/10/18
 */
@Component
public class AmazonHealth implements HealthIndicator {

    @Override
    public Health health() {
        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject("http://www.amazon.com", String.class);
            return Health.up().build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("reason", e.getMessage())
                    .build();
        }
    }
}
