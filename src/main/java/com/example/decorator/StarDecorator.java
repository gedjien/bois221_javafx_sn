package com.example.decorator;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class StarDecorator extends TreeDecorator {

    public StarDecorator(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public void Operation(Pane pane) {
        super.Operation(pane);
        drawStar(pane);
    }

    private void drawStar(Pane pane) {
        Polygon star = new Polygon();
        star.getPoints().addAll(200.0, 30.0, 210.0, 50.0, 230.0, 50.0, 215.0, 60.0, 225.0, 80.0, 200.0, 70.0, 175.0, 80.0, 185.0, 60.0, 170.0, 50.0, 190.0, 50.0);
        star.setFill(Color.YELLOW);
        pane.getChildren().add(star);
    }

    @Override
    public String decorate() {
        return super.decorate() + ", Star";
    }

    @Override
    public float cost() {
        return super.cost() + 10.0f;
    }
}
