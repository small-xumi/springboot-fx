package com.wanke.fxboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 程序入口
 * @author admin
 */
@SpringBootApplication
public class MainLaunch {

    public static void main(String[] args) {
        // 启动springboot
        ConfigurableApplicationContext context =
                SpringApplication.run(MainLaunch.class, args);
        // 启动javafx
        Launch.launch(context, args);
    }
}
