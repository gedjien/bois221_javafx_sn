package com.example.shapechasegame.view;

import com.example.shapechasegame.controller.GameSettings;
import com.example.shapechasegame.model.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView {
    private Pane root;
    private List<ShapeModel> shapes;
    private Random random;

    public GameView() {
        root = new Pane();
        shapes = new ArrayList<>();
        random = new Random();
        initializeShapes();
    }

    private void initializeShapes() {
        for(int i = 0; i < 4; i++) { // Добавляем 4 фигуры по умолчанию
            addNewShape();
        }
    }

    public Pane getRoot() {
        return root;
    }

    public List<ShapeModel> getShapes() {
        return shapes;
    }

    public void update() {
        for (ShapeModel shape : shapes) {
            shape.move();
        }
    }

    public void removeShape(ShapeModel shape) {
        shapes.remove(shape);
        root.getChildren().remove(shape.getShape());
    }

    public void addNewShape() {
        double size = 20 + random.nextInt(30);  // Случайный размер от 20 до 50
        ShapeModel newShape = createRandomShape(size);
        shapes.add(newShape);
        newShape.getShape().getProperties().put("shapeModel", newShape);
        root.getChildren().add(newShape.getShape());
    }

    private ShapeModel createRandomShape(double size) {
        double x = size + random.nextDouble() * (800 - 2 * size);
        double y = size + random.nextDouble() * (600 - 2 * size);
        Color color = GameSettings.getRandomColor();

        switch (random.nextInt(6)) {
            case 0:
                return new CircleShape(x, y, color, size);
            case 1:
                return new SquareShape(x, y, color, size);
            case 2:
                return new TriangleShape(x, y, color, size);
            case 3:
                return new RectangleShape(x, y, color, size);
            case 4:
                return new PentagonShape(x, y, color, size);
            case 5:
                return new HexagonShape(x, y, color, size);
            default:
                return new CircleShape(x, y, color, size);
        }
    }
}
