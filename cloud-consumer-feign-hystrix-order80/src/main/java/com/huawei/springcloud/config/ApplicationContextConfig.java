package com.huawei.springcloud.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangZiHao
 * @date 2023/12/27
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    public Request.Options dd(){
        return new Request.Options(5000,5000);
    }
}
