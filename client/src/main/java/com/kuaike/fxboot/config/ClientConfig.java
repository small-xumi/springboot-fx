package com.kuaike.fxboot.config;

import javafx.beans.property.SimpleDoubleProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Data
@Component
@ConfigurationProperties("client.config")
public class ClientConfig {
    /**
     * 自定义客户端名称
     */
    private String myAppName;
    /**
     * 默认窗口宽度
     */
    private SimpleDoubleProperty appWidth = new SimpleDoubleProperty(320);
    /**
     * 默认窗口高度
     */
    private SimpleDoubleProperty appHeight = new SimpleDoubleProperty(240);

}
