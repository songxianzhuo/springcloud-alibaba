package com.example.alibabadubboprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaDubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaDubboProviderApplication.class, args);
    }

}
