package com.huawei.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.huawei.springcloud.entities.CommonResult;
import com.huawei.springcloud.entities.Payment;
import com.huawei.springcloud.lb.LoadBalancer;

import java.net.URI;
import java.util.List;

/**
 * @author WangZiHao
 * @date 2023/12/26
 */
@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL= "http://CLOUD-PAYMENT-SERVICE";
//    public static final String PAYMENT_URL= "http://localhost:8001";
    @Autowired
    private LoadBalancer loadBalancer;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${spring.application.name}")
    private String serviceName;
    @GetMapping(value = {"/consumer/paymentlb1/get/{id}"})
    public CommonResult getPaymentlb1(@PathVariable("id") Long id){
        log.info("执行新的负载均衡");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping(value = {"/consumer/paymentlb/get/{id}"})
    public CommonResult getPaymentlb(@PathVariable("id") Long id){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (serviceInstances == null || serviceInstances.isEmpty()){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.getInstance(serviceInstances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping(value = {"/consumer/payment/create"})
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }
    @GetMapping(value = {"/consumer/payment/ribbon/create"})
    public CommonResult createRibbon(Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }
    @GetMapping(value = {"/consumer/payment/get/{id}"})
    public CommonResult getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping(value = {"/consumer/payment/ribbon/get/{id}"})
    public CommonResult getPaymentRibbon(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }
}
