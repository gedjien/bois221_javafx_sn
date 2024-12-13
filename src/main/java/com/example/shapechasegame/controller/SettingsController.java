package com.example.shapechasegame.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private ChoiceBox<String> difficultyChoice;

    @FXML
    private ChoiceBox<String> colorSchemeChoice;

    @FXML
    private ChoiceBox<String> movementTypeChoice; // Новый выбор для типа перемещения

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        difficultyChoice.getItems().addAll("Легкий", "Средний", "Сложный");
        colorSchemeChoice.getItems().addAll("Темная", "Светлая");
        movementTypeChoice.getItems().addAll("Телепортация", "Плавное перемещение"); // Настройки перемещения

        // Загрузка сохраненных настроек
        difficultyChoice.setValue(GameSettings.getDifficulty());
        colorSchemeChoice.setValue(GameSettings.getColorScheme());
        movementTypeChoice.setValue(GameSettings.getMovementType()); // Загрузка типа перемещения
    }

    @FXML
    private void handleSave() {
        GameSettings.setDifficulty(difficultyChoice.getValue());
        GameSettings.setColorScheme(colorSchemeChoice.getValue());
        GameSettings.setMovementType(movementTypeChoice.getValue()); // Сохранение типа перемещения

        // Закрытие окна настроек или переход обратно к игре
        Stage stage = (Stage) difficultyChoice.getScene().getWindow();
        stage.close();
    }
}
