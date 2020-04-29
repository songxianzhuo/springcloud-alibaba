package com.example.serviceconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/04/28
 **/

@FeignClient("sentinel-rate-limiting")
public interface SentinelRateLimiting {

    @GetMapping(value = "/hello2")
    String hello();
}
