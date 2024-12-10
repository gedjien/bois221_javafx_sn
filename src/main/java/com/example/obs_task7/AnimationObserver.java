package com.example.obs_task7;

import javafx.application.Platform;
import javafx.scene.shape.Circle;

//Наблюдатель, который анимирует движение круга.
//
//Метод update(Subject subject) обновляет позицию круга в зависимости от текущего состояния времени.

public class AnimationObserver implements IObserver {
    private Circle circle;
    private double startX;
    private double endX;
    private double duration;

    public AnimationObserver(Circle circle, double startX, double endX, double duration) {
        this.circle = circle;
        this.startX = startX;
        this.endX = endX;
        this.duration = duration;
    }

    @Override
    public void update(Subject subject) {
        TimeServer timeServer = (TimeServer) subject;
        int currentTime = timeServer.getState();
        double progress = (currentTime % duration) / duration;
        double currentX = startX + (endX - startX) * progress;

        Platform.runLater(() -> circle.setTranslateX(currentX));
    }
}

