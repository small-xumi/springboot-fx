package com.wanke.fxboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("client.config")
public class ClientConfig {
    /**
     * 自定义客户端名称
     */
    private String myAppName;

}
