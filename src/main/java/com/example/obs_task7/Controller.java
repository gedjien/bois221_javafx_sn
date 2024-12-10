package com.example.obs_task7;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    private Label timeLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Circle animationCircle;
    @FXML
    private Label musicLabel;

    private TimeServer timeServer;
    private TextObserver textObserver;
    private MediaObserver mediaObserver;
    private AnimationObserver animationObserver;
    private boolean isRunning = false;

    @FXML
    public void initialize() {
        timeServer = new TimeServer();
        textObserver = new TextObserver(timeLabel);
        mediaObserver = new MediaObserver(20, "C:/Users/gdjn/Desktop/animetierlist-backend-master/obs_Task7/vivo.mp3");
        animationObserver = new AnimationObserver(animationCircle, 0, 200, 20);

        timeServer.attach(textObserver);
        timeServer.attach(mediaObserver);
        timeServer.attach(animationObserver);

        // Инициализация метки о музыке
        musicLabel.setText("Музыка заиграет через 20 секунд");
    }

    @FXML
    public void startTimer() {
        if (!isRunning) {
            isRunning = true;
            timeServer.setState(0);
            timeServer.start();
        }
    }

    @FXML
    public void stopTimer() {
        if (isRunning) {
            isRunning = false;
            timeServer.stop();
            timeServer.setState(0); // Обнуление состояния времени
            mediaObserver.stopMedia(); // Остановка воспроизведения музыки
        }
    }
}
