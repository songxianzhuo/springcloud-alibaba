package com.example.nacosconfig.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/04/27
 **/
@Slf4j
@RestController
@RefreshScope
public class TestController {

    /**
     * 注解@RefreshScope，主要用来让这个类下的配置内容支持动态刷新，也就是当我们的应用启动之后，修改了Nacos中的配置内容之后，这里也会马上生效
     */

    /**
     * 默认是空字符串
     */
    @Value("${didispace.title:}")
    private String title;

    @Value("${name:}")
    private String name;

    @Value("${age:}")
    private Integer age;

    @Value("${user.age:}")
    private Integer userAge;

    @Value("${user.name:}")
    private String userName;

    @Value("${user.sex:}")
    private String userSex;


    @GetMapping("/test")
    public String hello() {
        System.out.println("title:" + title);
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        System.out.println("userName:" + userName);
        System.out.println("userAge:" + userAge);
        System.out.println("userSex:" + userSex);
        return title;
    }
}
