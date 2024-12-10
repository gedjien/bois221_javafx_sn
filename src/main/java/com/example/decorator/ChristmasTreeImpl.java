package com.example.decorator;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class ChristmasTreeImpl implements ChristmasTree {

    @Override
    public void Operation(Pane pane) {
        // Рисуем елку из трех треугольников
        Polygon tree1 = new Polygon();
        tree1.getPoints().addAll(200.0, 50.0, 150.0, 150.0, 250.0, 150.0);
        tree1.setFill(Color.GREEN);

        Polygon tree2 = new Polygon();
        tree2.getPoints().addAll(200.0, 100.0, 125.0, 200.0, 275.0, 200.0);
        tree2.setFill(Color.GREEN);

        Polygon tree3 = new Polygon();
        tree3.getPoints().addAll(200.0, 150.0, 100.0, 250.0, 300.0, 250.0);
        tree3.setFill(Color.GREEN);

        // Рисуем ствол
        Rectangle trunk = new Rectangle(175, 250, 50, 50);
        trunk.setFill(Color.BROWN);

        pane.getChildren().addAll(tree1, tree2, tree3, trunk);
    }

    @Override
    public String decorate() {
        return "Christmas Tree";
    }

    @Override
    public float cost() {
        return 50.0f;
    }
}
