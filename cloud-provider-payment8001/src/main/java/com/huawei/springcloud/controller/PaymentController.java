package com.huawei.springcloud.controller;

import ch.qos.logback.core.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import com.huawei.springcloud.entities.CommonResult;
import com.huawei.springcloud.entities.Payment;
import com.huawei.springcloud.service.PaymentService;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author WangZiHao
 * @date 2023/12/26
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping(value = {"/payment/gateway"})
    public String a(){
        return serverPort;
    }
    @GetMapping(value = {"/payment/discovery"})
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t" + instance.getUri());
            }
        }
        return discoveryClient;
    }

    @PostMapping(value = ("/payment/create"))
//    public CommonResult create(@RequestBody Payment payment){
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果1111 "+ result);
        if (result>0){
            return new CommonResult(200,"插入数据成功 serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据失败");
        }
    }
    @GetMapping(value = ("/payment/get/{id}"))
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        //这个sleep用于测试feign的超时时间,由于feign底层使用的ribbon调用服务，所以设置ribbon的超时时就可以了
        /*try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        Payment result = paymentService.getPaymentById(id);
        log.info("查询的结果" + result);
        if (result != null){
            return new CommonResult(200,"查询到数据 serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"没有查询到结果");
        }
    }
}
