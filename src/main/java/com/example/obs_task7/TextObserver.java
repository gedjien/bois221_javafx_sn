package com.example.obs_task7;

import javafx.application.Platform;
import javafx.scene.control.Label;

//Наблюдатель, который обновляет текстовую метку, отображая прошедшее время.
//
//Метод update(Subject subject) получает текущее состояние времени и обновляет метку.

public class TextObserver implements IObserver {
    private Label label;

    public TextObserver(Label label) {
        this.label = label;
    }

    @Override
    public void update(Subject subject) {
        TimeServer timeServer = (TimeServer) subject;
        Platform.runLater(() -> label.setText("Прошло " + timeServer.getState() + " с"));
    }
}
