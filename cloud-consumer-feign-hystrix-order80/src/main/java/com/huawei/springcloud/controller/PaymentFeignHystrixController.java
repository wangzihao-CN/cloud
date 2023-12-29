package com.huawei.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.huawei.springcloud.service.PaymentFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.concurrent.TimeUnit;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "default_fallback")
public class PaymentFeignHystrixController {
    @Autowired
    private PaymentFeignHystrixService paymentFeignHystrixService;
    @GetMapping(value = "/consumer/payment/hystrix/get/{id}")
    @HystrixCommand
    public String getPaymentById(@PathVariable("id") Integer id){
//        int i = 1/0;
       /* try {
            TimeUnit.SECONDS.sleep(6);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        String s = paymentFeignHystrixService.getPaymentById(id);
        log.info("result  "+s);
        return s;
    }
    @HystrixCommand(fallbackMethod = "timeout_fallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id){
        int d = 1/0;
        String s = paymentFeignHystrixService.payment_timeout(id);
        log.info("result  "+s);
        return s;
    }

    private String timeout_fallback(Integer id){
        return "线程池: "+ Thread.currentThread().getName()+"payment_timeout,id: "+id
                +"\t" +"我是消费者，系统功能繁忙，请10S后重试";
    }
    private String default_fallback(){
        return "全局fallback，请稍后重试";
    }
}
