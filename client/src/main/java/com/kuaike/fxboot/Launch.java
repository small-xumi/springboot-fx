package com.kuaike.fxboot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 这个交给springboot去启动，不要在这里启动，否则会报错
 *
 * @author admin
 */
@Component
public class Launch extends Application {
    // 任何地方都可以通过这个applicationContext获取springboot的上下文
    public static ConfigurableApplicationContext applicationContext;
    //启动参数
    private static String[] args;
    private static Stage mainStage;

    public void changeStage(Scene scene) {
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
        mainStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 将 Stage 注册为 Spring Bean
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/hello-view.fxml"));
        //这里需要注入controller的bean，可以让javafx的其他的类被springboot管理
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        mainStage = primaryStage;
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
        mainStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        //关闭springboot
        applicationContext.stop();
    }

    /**
     * 启动方法
     *
     * @param applicationContext
     * @param args
     */
    public static void launch(ConfigurableApplicationContext applicationContext, String[] args) {
        Launch.args = args;
        Launch.applicationContext = applicationContext;
        launch(args);
    }
}
