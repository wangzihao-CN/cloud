package com.huawei.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

/**
 * @author WangZiHao
 * @date 2023/12/26
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping(value = "/paymment/zk")
    public String paymentzk(){
        return "spring cloud with zookeeper: " + serverPort+"\t" + UUID.randomUUID();
    }
}
