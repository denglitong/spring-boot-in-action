package com.readinglist.readinglist;

import com.denglitong.readinglist.ReadinglistApplication;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * 使用Junit的@Test注解时需要在类头部加上@RunWith(SpringRunner.class)
 * -- 告诉Java你的测试函数是在Spring环境（会开启一些Spring环境下的自动配置，如属性注入等）
 *
 * @author litong.deng@foxmail.com
 * @date 2021/10/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ReadinglistApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleWebTest_RandomPort {

    @LocalServerPort
    private int randomPort;

    @Test(expected = HttpClientErrorException.class)
    public void pageNotFound() {
        System.out.println("randomPort: " + randomPort);
        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject("http://localhost:{port}/bogusPage", String.class, randomPort);
            Assertions.fail("Should result in hTTP 404");
        } catch (HttpClientErrorException e) {
            Assertions.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            throw e;
        }
    }

}
