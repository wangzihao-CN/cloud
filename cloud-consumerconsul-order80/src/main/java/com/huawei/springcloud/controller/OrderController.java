package com.huawei.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@RestController
@Slf4j
public class OrderController {
    public static final String INVOKE_URL = "http://cloud-payment-service";
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping(value = "/payment/consul/")
    public String dd(){
        return restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
    }
}
