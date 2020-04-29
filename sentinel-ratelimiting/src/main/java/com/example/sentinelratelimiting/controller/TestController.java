package com.example.sentinelratelimiting.controller;

import com.example.sentinelratelimiting.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    TestService testService;

    @GetMapping("/hello")
    public String hello() {
        testService.doSomeThing("hello everyone");
        testService.doAnyThing("bye everyone");
        return "didispace.com";
    }

    @GetMapping("/hello2")
    public String hello2() {
        testService.doSomeThing2("hello everyone");
        testService.doAnyThing("bye everyone");
        return "didispace.com";
    }

    @GetMapping("/test")
    public String test() {
        // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-provider");
        String url = serviceInstance.getUri() + "/hello?name=restTemplate";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(url);
        return result;
    }
}
