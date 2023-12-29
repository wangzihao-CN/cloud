package com.huawei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class gatewayNetty9527
{
    public static void main( String[] args )
    {
        SpringApplication.run(gatewayNetty9527.class,args);
    }
}
