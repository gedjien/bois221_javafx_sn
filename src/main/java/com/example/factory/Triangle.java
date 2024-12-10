package com.example.factory;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.BLUE);
        gr.setLineWidth(3);
        gr.strokePolygon(new double[]{150, 200, 250}, new double[]{150, 50, 150}, 3);
    }

    @Override
    public String descriptor() {
        return "Треугольник";
    }
}