package com.example.sentinelratelimiting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SentinelratelimitingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelratelimitingApplication.class, args);
    }


}
