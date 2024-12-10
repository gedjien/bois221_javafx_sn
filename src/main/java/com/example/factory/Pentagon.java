package com.example.factory;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.PURPLE);
        gr.setLineWidth(3);
        gr.strokePolygon(
                new double[]{150, 180, 220, 250, 200},
                new double[]{100, 50, 50, 100, 150},
                5
        );
    }

    @Override
    public String descriptor() {
        return "Пятиугольник";
    }
}
