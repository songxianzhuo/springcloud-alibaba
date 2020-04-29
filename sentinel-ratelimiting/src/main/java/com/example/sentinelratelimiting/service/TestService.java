package com.example.sentinelratelimiting.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/04/28
 **/
@Slf4j
@Service
public class TestService {

    /**
     * 使用@SentinelResource 对方法实现限流控制，类似于HystrixCommand中定义fallback
     * 通过blockHandler属性制定具体的处理函数，实现限流的异常处理
     * 学习链接：http://blog.didispace.com/spring-cloud-alibaba-sentinel-2-5/
     * @param str
     */
    @SentinelResource(value = "doSomeThing", blockHandler = "exceptionHandler")
    public void doSomeThing(String str) {
        log.info(str);
    }

    public void doAnyThing(String str) {
        log.info(str);
    }

    /**
     * 限流与阻塞处理
     * 实现处理函数，该函数的传参必须与资源点的传参一样，并且最后加上BlockException异常参数；同时，返回类型也必须一样
     * @param str
     * @param ex
     */
    public void exceptionHandler(String str, BlockException ex){
        log.error( "blockHandler：" + str, ex);
    }

    /**
     * 使用@SentinelResource注解实现熔断降级，实类似于Hystrix的熔断降级策略
     * 学习链接：http://blog.didispace.com/spring-cloud-alibaba-sentinel-2-5/
     * @param str
     */
    @SentinelResource(value = "doSomeThing2",fallback = "fallbackHandler")
    public void doSomeThing2(String str) {
        log.info(str);
        throw new RuntimeException("发生异常");
    }

    /**
     * 熔断降级处理函数
     * @param str
     */
    public void fallbackHandler(String str) {
        log.error("fallbackHandler：" + str);
    }
}
