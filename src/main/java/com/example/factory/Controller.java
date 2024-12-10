package com.example.factory;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.canvas.GraphicsContext;

public class Controller {

    @FXML
    private TextField valueField;

    @FXML
    private Canvas canvas;

    private final ShapeFactory shapeFactory = new ShapeFactory();

    @FXML
    public void addPicker() {
        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        try {
            int numberOfSides = Integer.parseInt(valueField.getText());
            Shape shape = shapeFactory.createShape(numberOfSides);

            if (shape != null) {
                shape.draw(gr);
            } else {
                showAlert("Число сторон вне диапазона!");
            }
        } catch (NumberFormatException e) {
            showAlert("Введите корректное число!");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
