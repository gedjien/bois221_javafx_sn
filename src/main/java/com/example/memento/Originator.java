package com.example.memento;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Originator {

    private Shape shape;

    public Originator(Shape shape) {
        this.shape = shape;
    }

    public Memento createMemento() {
        // Создание снимка состояния фигуры
        return new Memento(shape);
    }

    public void restoreMemento(Memento memento) {
        // Восстановление состояния фигуры из снимка
        memento.restoreState(shape);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
