package com.example.obs_task7;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

//Наблюдатель, который воспроизводит аудиофайл через определенные интервалы времени.
//
//Метод update(Subject subject) проверяет, если прошло достаточное количество времени для воспроизведения музыки, и запускает воспроизведение.

public class MediaObserver implements IObserver {
    private MediaPlayer mediaPlayer;
    private int interval;
    private int startTime;
    private boolean isPlaying;

    public MediaObserver(int interval, String mediaFile) {
        this.startTime = 0;
        this.interval = interval;
        Media media = new Media(new File(mediaFile).toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
        this.isPlaying = false;
    }

    @Override
    public void update(Subject subject) {
        TimeServer timeServer = (TimeServer) subject;
        int currentTime = timeServer.getState();
        if (currentTime >= startTime && currentTime % interval == 0) {
            if (!isPlaying) {
                mediaPlayer.play();
                isPlaying = true;
            }
        } else {
            if (isPlaying) {
                mediaPlayer.stop();
                isPlaying = false;
            }
        }
    }

    public void stopMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
