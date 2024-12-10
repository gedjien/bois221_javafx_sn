package com.example.decorator;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class PresentsDecorator extends TreeDecorator {

    public PresentsDecorator(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public void Operation(Pane pane) {
        super.Operation(pane);
        drawPresents(pane);
    }

    private void drawPresents(Pane pane) {
        Random random = new Random();
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK, Color.ORANGE};

        for (int i = 0; i < 5; i++) {
            Rectangle present = new Rectangle(30, 30, colors[random.nextInt(colors.length)]);
            present.setTranslateX(100 + i * 40);
            present.setTranslateY(280);
            pane.getChildren().add(present);
        }
    }

    @Override
    public String decorate() {
        return super.decorate() + ", Presents";
    }

    @Override
    public float cost() {
        return super.cost() + 20.0f;
    }
}
