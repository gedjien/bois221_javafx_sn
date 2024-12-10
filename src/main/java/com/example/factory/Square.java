package com.example.factory;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(Color.RED);
        gr.fillRect(100, 100, 150, 150);
    }

    @Override
    public String descriptor() {
        return "Квадрат";
    }
}

