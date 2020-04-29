package com.example.serviceconsumer.controller;

import com.example.serviceconsumer.ServiceconsumerApplication;
import com.example.serviceconsumer.service.SentinelRateLimiting;
import com.example.serviceconsumer.service.ServiceProviderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

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

    /**
     * 服务接口消费方式一
     * 使用原始的restTemplate
     * @return
     */
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

    @Autowired
    RestTemplate loadBalancedRestTemplate;

    /**
     * 服务接口消费方式一 plus
     * 使用springcloud增强后的restTemplate
     * @return
     */
    @GetMapping("/test2")
    public String test2() {
        String result = loadBalancedRestTemplate.getForObject("http://service-provider/hello?name=restTemplateplus", String.class);
        return result;
    }

    @Autowired
    private WebClient.Builder loadBalancedWebClientBuilder;

    /**
     * 服务接口消费方式二
     * 使用spring5.0最新引入的webClient进行服务调用
     * 详情连接：https://blog.csdn.net/iteye_13139/article/details/82726588
     * @return
     */
    @GetMapping("/test3")
    public Mono<String> test3() {
        Mono<String> result = loadBalancedWebClientBuilder.build()
                .get()
                .uri("http://service-provider/hello?name=webClient")
                .retrieve()
                .bodyToMono(String.class);
        return result;
    }

    @Resource
    ServiceProviderClient serviceProviderClient;

    /**
     * 服务接口消费方式三
     * 使用spring5.0最新引入的webClientBuilder进行服务调用
     * 详情连接：https://blog.csdn.net/iteye_13139/article/details/82726588
     * @return
     */
    @GetMapping("/test4")
    public String test4() {
        String result = serviceProviderClient.hello("feign");
        return "Return : " + result;
    }

    @Resource
    SentinelRateLimiting sentinelRateLimiting;

    @GetMapping("/test5")
    public String test5(){
        for (int i = 0; i < 10; i++) {
            try{
                String result = sentinelRateLimiting.hello();
                System.out.println(result);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "over";
    }
}
