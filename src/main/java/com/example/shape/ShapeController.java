package com.example.shape;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ShapeController {

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private String currentMode = "move"; // Режим работы: move, rectangle, circle, line
    private double startX, startY; // Координаты для рисования линий
    private Drawable selectedShape; // Текущая выбранная для перетаскивания фигура

    // Список фигур
    private final List<Drawable> shapes = new ArrayList<>();

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();

        // Слушатели для мыши
        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseDragged(this::onMouseDragged);
        canvas.setOnMouseReleased(this::onMouseReleased);
    }

    // Метод для добавления прямоугольника
    @FXML
    public void drawRectangle() {
        currentMode = "rectangle";
    }

    // Метод для добавления круга
    @FXML
    public void drawCircle() {
        currentMode = "circle";
    }

    // Метод для включения рисования линии
    @FXML
    public void enableLineDrawing() {
        currentMode = "line";
    }

    // Обработка нажатия мыши
    private void onMousePressed(MouseEvent event) {
        if ("rectangle".equals(currentMode)) {
            // Добавляем прямоугольник
            shapes.add(new Rectangle(event.getX(), event.getY(), 150, 100, Color.BLUE));
            currentMode = "move"; // Возврат в режим перетаскивания
            redrawCanvas();
        } else if ("circle".equals(currentMode)) {
            // Добавляем круг
            shapes.add(new Circle(event.getX(), event.getY(), 50, Color.RED));
            currentMode = "move"; // Возврат в режим перетаскивания
            redrawCanvas();
        } else if ("line".equals(currentMode)) {
            if (startX == 0 && startY == 0) {
                // Начальная точка линии
                startX = event.getX();
                startY = event.getY();
            } else {
                // Конечная точка линии
                shapes.add(new Line(startX, startY, event.getX(), event.getY(), Color.BLACK));
                currentMode = "move"; // Возврат в режим перетаскивания
                startX = 0;
                startY = 0;
                redrawCanvas();
            }
        } else if ("move".equals(currentMode)) {
            // Поиск фигуры, на которую кликнули, для перетаскивания
            selectedShape = findShapeAt(event.getX(), event.getY());
        }
    }

    // Обработка перемещения мыши (перетаскивание фигуры)
    private void onMouseDragged(MouseEvent event) {
        if (selectedShape != null && "move".equals(currentMode)) {
            selectedShape.move(event.getX(), event.getY());
            redrawCanvas();
        }
    }

    // Обработка отпускания мыши
    private void onMouseReleased(MouseEvent event) {
        selectedShape = null; // Сбрасываем выбранную фигуру
    }

    // Перерисовка холста
    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Drawable shape : shapes) {
            shape.draw(gc);
        }
    }

    // Поиск фигуры по координатам
    private Drawable findShapeAt(double x, double y) {
        for (Drawable shape : shapes) {
            if (shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }

    // Интерфейс для всех фигур
    interface Drawable {
        void draw(GraphicsContext gc);

        void move(double x, double y);

        boolean contains(double x, double y); // Проверка, содержит ли фигура точку
    }

    // Прямоугольник
    static class Rectangle implements Drawable {
        private double x, y, width, height;
        private Color color;

        public Rectangle(double x, double y, double width, double height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        @Override
        public void draw(GraphicsContext gc) {
            gc.setFill(color);
            gc.fillRect(x, y, width, height);
        }

        @Override
        public void move(double x, double y) {
            this.x = x - width / 2;
            this.y = y - height / 2;
        }

        @Override
        public boolean contains(double x, double y) {
            return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
        }
    }

    // Круг
    static class Circle implements Drawable {
        private double x, y, radius;
        private Color color;

        public Circle(double x, double y, double radius, Color color) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void draw(GraphicsContext gc) {
            gc.setFill(color);
            gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }

        @Override
        public void move(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean contains(double x, double y) {
            double dx = x - this.x;
            double dy = y - this.y;
            return dx * dx + dy * dy <= radius * radius;
        }
    }

    // Линия
    static class Line implements Drawable {
        private double startX, startY, endX, endY;
        private Color color;

        public Line(double startX, double startY, double endX, double endY, Color color) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.color = color;
        }

        @Override
        public void draw(GraphicsContext gc) {
            gc.setStroke(color);
            gc.setLineWidth(2);
            gc.strokeLine(startX, startY, endX, endY);
        }

        @Override
        public void move(double x, double y) {
            // Рассчитываем смещение для перемещения линии
            double deltaX = x - (startX + endX) / 2; // Сдвиг от центра линии
            double deltaY = y - (startY + endY) / 2;

            startX += deltaX;
            startY += deltaY;
            endX += deltaX;
            endY += deltaY;
        }

        @Override
        public boolean contains(double x, double y) {
            // Проверяем, находится ли точка рядом с линией
            double dx = endX - startX;
            double dy = endY - startY;
            double length = Math.sqrt(dx * dx + dy * dy);

            if (length == 0) {
                // Если линия вырождается в точку
                return Math.hypot(x - startX, y - startY) < 5;
            }

            double distance = Math.abs(dy * x - dx * y + endX * startY - endY * startX) / length;
            double projection = ((x - startX) * dx + (y - startY) * dy) / length;

            // Проверяем, что точка находится близко к линии и внутри её длины
            return distance <= 5 && projection >= 0 && projection <= length;
        }
    }
}
