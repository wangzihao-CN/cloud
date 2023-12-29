package com.huawei.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangZiHao
 * @date 2023/12/29
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}") //gitee里的yml文件里的内容
    private String configInfo;

    @RequestMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}