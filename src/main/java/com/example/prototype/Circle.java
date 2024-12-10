package com.example.prototype;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(color);
        gc.fillOval(x - 25, y - 25, 50, 50);
    }

    @Override
    public String toString() {
        return "Circle";
    }
}
