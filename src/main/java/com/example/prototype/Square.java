package com.example.prototype;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {
    public Square() {
        type = "Square";
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(color);
        gc.fillRect(x - 25, y - 25, 50, 50);
    }

    @Override
    public String toString() {
        return "Square";
    }
}
