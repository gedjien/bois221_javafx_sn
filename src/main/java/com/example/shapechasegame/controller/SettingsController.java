package com.example.shapechasegame.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import com.example.shapechasegame.model.ShapeType;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private ChoiceBox<String> difficultyChoice;

    @FXML
    private ChoiceBox<String> colorSchemeChoice;

    @FXML
    private ChoiceBox<String> movementTypeChoice;

    @FXML
    private ChoiceBox<String> playerShapeChoice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        difficultyChoice.getItems().addAll("Легкий", "Средний", "Сложный");
        colorSchemeChoice.getItems().addAll("Темная", "Светлая");
        movementTypeChoice.getItems().addAll("Телепортация", "Плавное перемещение");
        playerShapeChoice.getItems().addAll("Круг", "Квадрат", "Треугольник", "Прямоугольник", "Пятиугольник", "Шестиугольник");

        // Загрузка сохраненных настроек
        difficultyChoice.setValue(GameSettings.getDifficulty());
        colorSchemeChoice.setValue(GameSettings.getColorScheme());
        movementTypeChoice.setValue(GameSettings.getMovementType());
        playerShapeChoice.setValue(getShapeName(GameSettings.getPlayerShapeType()));
    }

    @FXML
    private void handleSave() {
        GameSettings.setDifficulty(difficultyChoice.getValue());
        GameSettings.setColorScheme(colorSchemeChoice.getValue());
        GameSettings.setMovementType(movementTypeChoice.getValue()); // Сохранение типа перемещения

        ShapeType selectedShapeType = getShapeType(playerShapeChoice.getValue());
        GameSettings.setPlayerShapeType(selectedShapeType);

        // Закрытие окна настроек или переход обратно к игре
        Stage stage = (Stage) difficultyChoice.getScene().getWindow();
        stage.close();
    }

    private ShapeType getShapeType(String shapeName) {
        switch (shapeName) {
            case "Круг":
                return ShapeType.CIRCLE;
            case "Квадрат":
                return ShapeType.SQUARE;
            case "Треугольник":
                return ShapeType.TRIANGLE;
            case "Прямоугольник":
                return ShapeType.RECTANGLE;
            case "Пятиугольник":
                return ShapeType.PENTAGON;
            case "Шестиугольник":
                return ShapeType.HEXAGON;
            default:
                return ShapeType.CIRCLE;
        }
    }

    private String getShapeName(ShapeType shapeType) {
        switch (shapeType) {
            case CIRCLE:
                return "Круг";
            case SQUARE:
                return "Квадрат";
            case TRIANGLE:
                return "Треугольник";
            case RECTANGLE:
                return "Прямоугольник";
            case PENTAGON:
                return "Пятиугольник";
            case HEXAGON:
                return "Шестиугольник";
            default:
                return "Круг";
        }
    }
}
