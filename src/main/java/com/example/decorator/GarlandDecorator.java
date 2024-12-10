package com.example.decorator;

import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class GarlandDecorator extends TreeDecorator {

    public GarlandDecorator(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public void Operation(Pane pane) {
        super.Operation(pane);
        drawGarland(pane);
    }

    private void drawGarland(Pane pane) {
        for (int i = 0; i < 7; i++) {
            Circle circle = new Circle(10);
            circle.setFill(Color.RED);
            circle.setTranslateX(150 + i * 20);
            circle.setTranslateY(130 + i * 10);
            pane.getChildren().add(circle);
            animateGarland(circle);
        }
    }

    private void animateGarland(Circle circle) {
        FadeTransition fade = new FadeTransition();
        fade.setNode(circle);
        fade.setDuration(Duration.seconds(1));
        fade.setFromValue(1.0);
        fade.setToValue(0.3);
        fade.setCycleCount(FadeTransition.INDEFINITE);
        fade.setAutoReverse(true);
        fade.play();
    }

    @Override
    public String decorate() {
        return super.decorate() + ", Garland";
    }

    @Override
    public float cost() {
        return super.cost() + 15.0f;
    }
}
