package com.kuaike.fxboot.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author yinyin
 * @date 2025/1/18
 */
@Controller
public class VideoController implements Initializable {
    @FXML
    private ListView<String> episodeList; // 选集列表
    @FXML
    private MediaView mediaView; // 视频播放区域
    @FXML
    private Button playButton; // 播放按钮
    @FXML
    private Button pauseButton; // 暂停按钮
    private MediaPlayer mediaPlayer; // 媒体播放器
    private List<String> videoFiles; // 视频文件列表
    private int currentIndex = 0; // 当前播放的视频索引

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        {
            // 初始化视频文件列表
            videoFiles = new ArrayList<>();
            videoFiles.add("video1.mp4");
            videoFiles.add("video2.mp4");
            videoFiles.add("video3.mp4");

            // 将视频文件列表显示在 ListView 中
            episodeList.getItems().addAll(videoFiles);

            // 默认加载第一个视频
            loadVideo(currentIndex);

            // 监听选集列表的点击事件
            episodeList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null && newValue.intValue() != currentIndex) {
                    currentIndex = newValue.intValue();
                    loadVideo(currentIndex);
                }
            });
        }
    }

    // 加载视频
    private void loadVideo(int index) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        String videoFile = videoFiles.get(index);
        try {
            Media media = new Media(new File(videoFile).toURI().toURL().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // 播放或暂停
    @FXML
    private void playOrPause() {
        if (mediaPlayer != null) {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.play();
            }
        }
    }

    // 播放上一集
    @FXML
    private void playPrevious() {
        if (currentIndex > 0) {
            currentIndex--;
            loadVideo(currentIndex);
            episodeList.getSelectionModel().select(currentIndex);
        }
    }

    // 播放下一集
    @FXML
    private void playNext() {
        if (currentIndex < videoFiles.size() - 1) {
            currentIndex++;
            loadVideo(currentIndex);
            episodeList.getSelectionModel().select(currentIndex);
        }
    }
}
