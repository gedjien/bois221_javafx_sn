package com.example.prototype;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    public Triangle() {
        type = "Triangle";
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(color);
        gc.fillPolygon(new double[]{x, x - 25, x + 25}, new double[]{y - 25, y + 25, y + 25}, 3);
    }

    @Override
    public String toString() {
        return "Triangle";
    }
}
