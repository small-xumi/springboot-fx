package com.kuaike.fxboot.controller;

import com.kuaike.fxboot.config.ClientConfig;
import jakarta.annotation.Resource;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author yinyin
 * @date 2025/2/10
 */
@Controller
public class MainController implements Initializable {
    @FXML
    private BorderPane mainPane;
    @Resource
    private ClientConfig clientConfig;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 创建左侧的VBox
        VBox leftMenu = new VBox(10); // 10是子节点之间的间距
        leftMenu.setStyle("-fx-padding: 10; -fx-background-color: #f4f4f4;");
        mainPane.prefHeightProperty().bind(clientConfig.getAppHeight());
        mainPane.prefWidthProperty().bind(clientConfig.getAppWidth());
        // 创建按钮
        Button btnInterfaceManagement = new Button("接口管理");
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: red;");
        Button btnAutomatedTesting = new Button("自动化测试");
        Button btnShareDocuments = new Button("分享文档");
        Button btnRequestHistory = new Button("请求历史");
        Button btnProjectSettings = new Button("项目设置");
        Button btnInviteMembers = new Button("邀请成员");
        VBox.setVgrow(vBox, Priority.ALWAYS);
        // 将按钮添加到VBox
        leftMenu.getChildren().addAll(btnInterfaceManagement, btnAutomatedTesting, vBox, btnShareDocuments,
                btnRequestHistory, btnProjectSettings, btnInviteMembers);
        // 将VBox设置到BorderPane的左侧
        mainPane.setLeft(leftMenu);
    }
}
