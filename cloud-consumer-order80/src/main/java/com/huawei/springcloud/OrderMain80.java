package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import com.huawei.myRule.MySelfRule;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE")
public class OrderMain80
{
    public static void main( String[] args )
    {
        SpringApplication.run(OrderMain80.class,args);
    }
}
