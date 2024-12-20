package com.example.shapechasegame.controller;

import com.example.shapechasegame.Main;
import com.example.shapechasegame.model.ShapeModel;
import com.example.shapechasegame.model.ShapeType;
import com.example.shapechasegame.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; // Добавлен импорт
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Random;
import javafx.scene.ImageCursor;

public class GameController implements Initializable {
    @FXML
    private ImageView backgroundImage;

    @FXML
    private Pane gamePane;

    @FXML
    private Label scoreLabel;

    private GameView gameView;
    private AnimationTimer timer;
    private int score = 0;

    private double targetX;
    private double targetY;
    private static final double SPEED = 2.0; // скорость перемещения

    private Shape player;
    private ShapeType playerShapeType = ShapeType.CIRCLE; // Тип фигуры игрока

    private Timeline shapeChangeTimeline;
    private Random random = new Random();

    private List<Color> rainbowColors = Arrays.asList(
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE,
            Color.INDIGO,
            Color.VIOLET
    );
    private int currentColorIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameView = new GameView();
        gamePane.getChildren().add(gameView.getRoot());

        Image cursorImage = new Image(getClass().getResource("/com/example/shapechasegame/view/images/custom_cursor.png").toExternalForm());
        ImageCursor customCursor = new ImageCursor(cursorImage, 0, 0);
        gamePane.setCursor(customCursor);

        // Загрузка изображения фона
        try {
            Image bgImage = new Image(getClass().getResource("/com/example/shapechasegame/view/images/background.jpg").toString());
            backgroundImage.setImage(bgImage);
        } catch (Exception e) {
            System.err.println("Не удалось загрузить изображение фона: " + e.getMessage());
        }

        // Установка фигуры игрока на основе настроек
        playerShapeType = GameSettings.getPlayerShapeType();
        Color initialColor = getNextRainbowColor();
        setPlayerShape(playerShapeType, initialColor);

        if (player != null) {
            targetX = player.getTranslateX();
            targetY = player.getTranslateY();
        } else {
            System.err.println("Player is not initialized!");
            // Установка значений по умолчанию
            targetX = 400;
            targetY = 300;
        }

        // Обработка кликов мыши
        gamePane.addEventFilter(MouseEvent.MOUSE_CLICKED, this::handleMouseInput);

        // Настройка и запуск Timeline для изменения фигуры и цвета каждые 5 секунд
        setupShapeChangeTimeline();

        startGame();
    }

    private void setupShapeChangeTimeline() {
        shapeChangeTimeline = new Timeline(
                new KeyFrame(Duration.seconds(5), event -> changePlayerShape())
        );
        shapeChangeTimeline.setCycleCount(Timeline.INDEFINITE); // Бесконечный цикл
        shapeChangeTimeline.play(); // Запуск Timeline
    }

    private void changePlayerShape() {
        ShapeType newShapeType = getRandomShapeTypeExcluding(playerShapeType);
        Color newColor = getNextRainbowColor();
        System.out.println("Изменение фигуры игрока на: " + newShapeType + " и цвет на: " + newColor);
        setPlayerShape(newShapeType, newColor);
    }

    private Color getNextRainbowColor() {
        Color color = rainbowColors.get(currentColorIndex);
        currentColorIndex = (currentColorIndex + 1) % rainbowColors.size();
        return color;
    }

    private ShapeType getRandomShapeTypeExcluding(ShapeType excludeType) {
        List<ShapeType> shapeTypes = Arrays.asList(ShapeType.values());
        List<ShapeType> filteredShapes = new ArrayList<>();
        for (ShapeType shape : shapeTypes) {
            if (shape != excludeType) {
                filteredShapes.add(shape);
            }
        }
        if (filteredShapes.isEmpty()) {
            return excludeType; // Если нет других фигур, вернуть текущую
        }
        int index = random.nextInt(filteredShapes.size());
        return filteredShapes.get(index);
    }

    private void setPlayerShape(ShapeType shapeType, Color color) {
        System.out.println("Установка фигуры игрока: " + shapeType + " с цветом: " + color);
        if (player != null) {
            gamePane.getChildren().remove(player);
        }

        switch (shapeType) {
            case CIRCLE:
                this.player = new Circle(20, color);
                break;
            case SQUARE:
                this.player = new Rectangle(40, 40, color);
                break;
            case TRIANGLE:
                Polygon triangle = new Polygon();
                double size = 20;
                triangle.getPoints().addAll(
                        0.0, -size,
                        size, size,
                        -size, size
                );
                triangle.setFill(color);
                this.player = triangle;
                break;
            case RECTANGLE:
                this.player = new Rectangle(60, 40, color);
                break;
            case PENTAGON:
                Polygon pentagon = new Polygon();
                size = 20;
                for(int i = 0; i < 5; i++) {
                    pentagon.getPoints().addAll(
                            size * Math.cos(Math.toRadians(72 * i - 90)),
                            size * Math.sin(Math.toRadians(72 * i - 90))
                    );
                }
                pentagon.setFill(color);
                this.player = pentagon;
                break;
            case HEXAGON:
                Polygon hexagon = new Polygon();
                size = 20;
                for(int i = 0; i < 6; i++) {
                    hexagon.getPoints().addAll(
                            size * Math.cos(Math.toRadians(60 * i - 90)),
                            size * Math.sin(Math.toRadians(60 * i - 90))
                    );
                }
                hexagon.setFill(color);
                this.player = hexagon;
                break;
            default:
                this.player = new Circle(20, color);
        }

        // Проверка инициализации игрока
        if (this.player == null) {
            System.err.println("Не удалось создать фигуру игрока для ShapeType: " + shapeType);
            return;
        }

        this.player.setTranslateX(400);
        this.player.setTranslateY(300);
        gamePane.getChildren().add(this.player);

        System.out.println("Фигура игрока установлена: " + this.player.getClass().getSimpleName());

        // Обновление логической переменной `playerShapeType`
        this.playerShapeType = shapeType;
        System.out.println("playerShapeType обновлён до: " + this.playerShapeType);
    }

    @FXML
    private void handleMouseInput(MouseEvent event) {
        if (GameSettings.getMovementType().equals("Телепортация")) {
            player.setTranslateX(event.getX());
            player.setTranslateY(event.getY());
        } else {
            targetX = event.getX();
            targetY = event.getY();
        }
    }

    private void startGame() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
                checkCollisions();
                if (!GameSettings.getMovementType().equals("Телепортация")) {
                    movePlayer();
                }
            }
        };
        timer.start();
    }

    private void updateGame() {
        gameView.update();
    }

    private void checkCollisions() {
        // Создаём временный список для сбора фигур, которые нужно удалить
        List<ShapeModel> shapesToRemove = new ArrayList<>();

        // Итерация по текущим фигурам
        for (ShapeModel shape : gameView.getShapes()) {
            if (player.getBoundsInParent().intersects(shape.getShape().getBoundsInParent())) {
                if (shape.getShapeType() == playerShapeType) { // Проверка типа фигуры
                    // Добавляем фигуру в список для удаления
                    shapesToRemove.add(shape);
                    score++;
                    updateScoreLabel();
                }
            }
        }

        // Удаление и добавление фигур после итерации
        for (ShapeModel shape : shapesToRemove) {
            gameView.removeShape(shape);
            gameView.addNewShape();
        }
    }

    private void movePlayer() {
        double deltaX = targetX - player.getTranslateX();
        double deltaY = targetY - player.getTranslateY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (distance > SPEED) {
            player.setTranslateX(player.getTranslateX() + (deltaX / distance) * SPEED);
            player.setTranslateY(player.getTranslateY() + (deltaY / distance) * SPEED);
        } else {
            player.setTranslateX(targetX);
            player.setTranslateY(targetY);
        }
    }

    public void setUpKeyHandlers(Stage stage) {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, event -> handleMovement(event.getCode()));
    }

    private void handleMovement(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                player.setTranslateY(Math.max(player.getTranslateY() - 5, 0));
                break;
            case DOWN:
                player.setTranslateY(Math.min(player.getTranslateY() + 5, 580));
                break;
            case LEFT:
                player.setTranslateX(Math.max(player.getTranslateX() - 5, 0));
                break;
            case RIGHT:
                player.setTranslateX(Math.min(player.getTranslateX() + 5, 780));
                break;
        }
    }

    @FXML
    private void openSettings() {
        try {
            // Получение текущего Stage из gamePane
            Stage currentStage = (Stage) gamePane.getScene().getWindow();

            // Вызов статического метода showSettings
            Main.showSettings(currentStage);

            // Обновление фигуры игрока после закрытия настроек
            playerShapeType = GameSettings.getPlayerShapeType();
            Color newColor = getNextRainbowColor();
            setPlayerShape(playerShapeType, newColor);

            updateShapeSpeeds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateShapeSpeeds() {
        double speedMultiplier = GameSettings.getSpeedMultiplier();
        for (ShapeModel shape : gameView.getShapes()) {
            double newVelocityX = shape.getVelocityX() / speedMultiplier;
            double newVelocityY = shape.getVelocityY() / speedMultiplier;
            shape.setVelocity(newVelocityX, newVelocityY);
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Счет: " + score);
    }

    // Метод для остановки Timeline при закрытии приложения
    public void stopShapeChangeTimeline() {
        if (shapeChangeTimeline != null) {
            shapeChangeTimeline.stop();
        }
    }
}
