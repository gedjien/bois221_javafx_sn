package com.example.indicatorapp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class IndicatorController {

    @FXML
    private Pane indicatorPane;

    private Timeline timeline;
    private int remainingSeconds = 60; // 60 секунд для одной минуты
    private Arc timeArc;
    private Label timeLabel;

    @FXML
    public void initialize() {
        initializeIndicator();
        startTimer();
    }

    private void initializeIndicator() {
        timeArc = new Arc();
        timeArc.setFill(Color.RED);
        timeArc.setStroke(Color.BLACK);
        timeArc.setStrokeWidth(2);
        timeArc.setCenterX(100);
        timeArc.setCenterY(100);
        timeArc.setRadiusX(50);
        timeArc.setRadiusY(50);
        timeArc.setStartAngle(90);
        timeArc.setType(ArcType.ROUND);
        timeArc.setLength(360);

        timeLabel = new Label("60 sec");
        timeLabel.setLayoutX(75);
        timeLabel.setLayoutY(200);

        indicatorPane.getChildren().addAll(timeArc, timeLabel);
    }

    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateIndicator()));
        timeline.setCycleCount(remainingSeconds);
        timeline.play();
    }

    private void updateIndicator() {
        remainingSeconds--;
        double angle = 360 * (remainingSeconds / 60.0); // Угол соответствует оставшемуся времени
        timeArc.setLength(angle);
        timeLabel.setText(remainingSeconds + " sec");

        if (remainingSeconds <= 0) {
            timeline.stop();
        }
    }
}
