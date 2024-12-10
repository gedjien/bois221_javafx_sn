package com.example.factory;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Straight implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.PINK);
        gr.setLineWidth(3);
        gr.strokeLine(100, 100, 250, 250);
    }

    @Override
    public String descriptor() {
        return "Линия";
    }
}
