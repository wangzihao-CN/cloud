package com.huawei.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @author WangZiHao
 * @date 2023/12/26
 */
@RestController
@Slf4j
public class OrderzkController {
    @Value("${server.port}")
    private String serverPort;

    public static final String INVOKE_URL = "http://cloud-payment-service";
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping(value = "/consumer/zk")
    public String consumerzk(){
        return restTemplate.getForObject(INVOKE_URL+"/paymment/zk",String.class);
    }
}
