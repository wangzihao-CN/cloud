package com.huawei.springcloud.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@Configuration
public class ApplicatonContextConfig {
  /*  @Bean
    public Request.Options requestOptions(){
        return new Request.Options(5000, 10000);
    }*/
  @Bean
  Logger.Level feignLoggerLevel(){
    return Logger.Level.FULL;
  }
}
