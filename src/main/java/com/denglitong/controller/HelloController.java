package com.denglitong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/11
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String hello() {
        return "Hello World";
    }
}
