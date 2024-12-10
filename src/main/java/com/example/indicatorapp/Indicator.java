package com.example.indicatorapp;

import javafx.scene.layout.Pane;

public class Indicator {

    private Pane panel = new Pane();

    public void add(Pane pane) {
        panel.getChildren().add(pane);
    }

    public void show(Pane parentPane) {
        parentPane.getChildren().add(panel);
    }

    // Дополнительные методы для управления индикатором
}
