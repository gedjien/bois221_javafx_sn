package com.example.prototype;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(color);
        gc.fillRect(x - 50, y - 25, 100, 50); // Прямоугольник размером 100x50 пикселей
    }

    @Override
    public String toString() {
        return "Rectangle";
    }
}
