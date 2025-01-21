package com.wanke.videofx.utils;

import com.wanke.videofx.Launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

/**
 * @author yinyin
 * @date 2025/1/18
 */
public class FXMLLoadUtil {


    /**
     * 都应该交由springboot管理
     *
     * @param location the location used to resolve relative path attribute values
     * @since JavaFX 2.1
     */
    public static Parent loader(URL location) throws IOException {
        // 加载第二个页面的 FXML 文件
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        fxmlLoader.setControllerFactory(Launch.applicationContext::getBean); //
        return fxmlLoader.load();
    }
}
