package com.example.memento;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Memento {
    private double layoutX, layoutY;
    private double width;
    private Color color;

    public Memento(Shape shape) {
        this.layoutX = shape.getLayoutX();
        this.layoutY = shape.getLayoutY();
        this.width = shape.getStrokeWidth();
        this.color = (Color) shape.getStroke();
    }

    public void restoreState(Shape shape) {
        shape.setLayoutX(layoutX);
        shape.setLayoutY(layoutY);
        shape.setStrokeWidth(width);
        shape.setStroke(color);
    }
}
