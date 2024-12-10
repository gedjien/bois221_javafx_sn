package com.example.memento;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private Pane canvas;

    private List<Shape> selectedShapes = new ArrayList<>(); // Список выбранных фигур
    private List<Double> initialX = new ArrayList<>(); // Начальные координаты X для каждой фигуры
    private List<Double> initialY = new ArrayList<>(); // Начальные координаты Y для каждой фигуры
    private double dragStartX, dragStartY; // Начальные координаты перетаскивания

    @FXML
    public void initialize() {
        // Инициализация холста и других компонентов, если нужно
    }

    // Метод для добавления новой фигуры на холст
    @FXML
    public void handleAddShape() {
        Random random = new Random();
        Shape shape;
        int shapeType = random.nextInt(3); // Случайный выбор типа фигуры (0 - прямоугольник, 1 - круг, 2 - эллипс)

        switch (shapeType) {
            case 0:
                shape = new Rectangle(100, 100, Color.BLUE);
                shape.setStroke(Color.BLACK);
                shape.setStrokeWidth(2);
                break;
            case 1:
                shape = new Circle(50, Color.GREEN); // Круг радиусом 50
                shape.setStroke(Color.BLACK);
                shape.setStrokeWidth(2);
                break;
            case 2:
                shape = new Ellipse(100, 50, 50, 25); // Эллипс с центром (100, 50) и радиусами 50 и 25
                shape.setFill(Color.PALEGOLDENROD);
                shape.setStroke(Color.BLACK);
                shape.setStrokeWidth(2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + shapeType);
        }

        // Устанавливаем начальные координаты
        shape.setLayoutX(100);
        shape.setLayoutY(100);

        shape.setOnMousePressed(this::onMousePressed);
        shape.setOnMouseDragged(this::onMouseDragged);
        shape.setOnMouseReleased(this::onMouseReleased);

        canvas.getChildren().add(shape);
    }



    // Обработчик нажатия на фигуру
    private void onMousePressed(MouseEvent event) {
        Shape clickedShape = (Shape) event.getSource();

        // Проверка на null
        if (clickedShape == null) {
            return;
        }

        // Если нажата правая кнопка мыши, снимаем выделение
        if (event.getButton() == MouseButton.SECONDARY) {
            clearSelection(); // Снимаем выделение с всех фигур
        }

        // Выбираем или отменяем выбор фигуры при клике левой кнопкой мыши
        if (event.getButton() == MouseButton.PRIMARY) {
            if (!selectedShapes.contains(clickedShape)) {
                // Добавляем фигуру в список выбранных
                selectedShapes.add(clickedShape);

                // Запоминаем текущие координаты для каждой фигуры (сейчас X и Y на холсте)
                initialX.add(clickedShape.getLayoutX());
                initialY.add(clickedShape.getLayoutY());

                // Выделяем фигуру
                clickedShape.setStrokeWidth(4);
                clickedShape.setStroke(Color.RED);
            } else {
                // Если фигура уже была выбрана, просто обновляем начальные координаты перетаскивания
                int index = selectedShapes.indexOf(clickedShape);
                initialX.set(index, clickedShape.getLayoutX());
                initialY.set(index, clickedShape.getLayoutY());
            }
        }

        // Запоминаем начальные координаты перетаскивания
        dragStartX = event.getSceneX();
        dragStartY = event.getSceneY();
    }

    // Обработчик перетаскивания фигур
    private void onMouseDragged(MouseEvent event) {
        if (!selectedShapes.isEmpty()) {
            // Рассчитываем смещение курсора
            double deltaX = event.getSceneX() - dragStartX;
            double deltaY = event.getSceneY() - dragStartY;

            // Обновляем позиции выбранных фигур с учетом смещения
            for (int i = 0; i < selectedShapes.size(); i++) {
                Shape shape = selectedShapes.get(i);
                shape.setLayoutX(initialX.get(i) + deltaX);
                shape.setLayoutY(initialY.get(i) + deltaY);
            }
        }
    }

    // Обработчик окончания перетаскивания
    private void onMouseReleased(MouseEvent event) {
        // Сохраняем новое состояние после перемещения
        for (int i = 0; i < selectedShapes.size(); i++) {
            initialX.set(i, selectedShapes.get(i).getLayoutX());
            initialY.set(i, selectedShapes.get(i).getLayoutY());
        }
    }

    // Сбрасываем выделение с всех фигур
    private void clearSelection() {
        // Снимаем выделение с каждой фигуры
        for (Shape shape : selectedShapes) {
            shape.setStrokeWidth(2);
            shape.setStroke(Color.BLACK);
        }

        // Очищаем список выбранных фигур
        selectedShapes.clear();
        initialX.clear();
        initialY.clear();
    }
}
