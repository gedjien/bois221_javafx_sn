package com.example.factory;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Angle implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.ORANGE);
        gr.setLineWidth(3);
        gr.strokeLine(100, 100, 200, 100);
        gr.strokeLine(200, 100, 200, 200);
    }

    @Override
    public String descriptor() {
        return "Угол";
    }
}
