package com.example.factory;
import javafx.scene.canvas.GraphicsContext;

public interface Shape {
    void draw(GraphicsContext gr);
    String descriptor();
}