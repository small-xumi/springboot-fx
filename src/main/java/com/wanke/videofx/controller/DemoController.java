package com.wanke.videofx.controller;

import com.wanke.videofx.Launch;
import com.wanke.videofx.config.ClientConfig;
import com.wanke.videofx.utils.FXMLLoadUtil;
import jakarta.annotation.Resource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author admin
 */
@Controller
public class DemoController implements Initializable {
    @FXML
    private Label welcomeText;
    @Resource
    private ClientConfig clientConfig;
    @Resource
    private Launch launch;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!" + clientConfig.getMyAppName());
        // 加载第二个页面的 FXML 文件
        URL resource = getClass().getResource("/views/hello-view.fxml");
        Scene scene = new Scene(FXMLLoadUtil.loader(resource), 500, 500);
        launch.changeStage(scene);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}