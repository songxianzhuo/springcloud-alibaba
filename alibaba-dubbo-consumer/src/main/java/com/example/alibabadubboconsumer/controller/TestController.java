package com.example.alibabadubboconsumer.controller;

import com.example.alibabadubboapi.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/04/28
 **/
@Slf4j
@RestController
public class TestController {

    /**
     * @Reference注解是 org.apache.dubbo.config.annotation.Reference
     */
    @Reference
    HelloService helloService;

    @GetMapping("/test")
    public String test() {
        String result = helloService.hello("didispace.com");
        System.out.println(result);
        return result;
    }
}
