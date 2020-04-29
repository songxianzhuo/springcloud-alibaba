package com.example.alibabadubboprovider.service;

import com.example.alibabadubboapi.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 描述
 * @Service注解是org.apache.dubbo.config.annotation.Service
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/04/28
 **/
@Service
public class HelloServiceImpl implements HelloService {


    @Override
    public String hello(String name) {
        System.out.println(name);
        return "hello " + name;
    }
}
