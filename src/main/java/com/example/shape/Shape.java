package com.example.shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Color color;
    protected double x, y;

    public Shape(Color color, double x, double y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    // Абстрактные методы
    public abstract double area();
    public abstract void draw(GraphicsContext gc);

    // Установка цвета
    public void setColor(Color color) {
        this.color = color;
    }
}
