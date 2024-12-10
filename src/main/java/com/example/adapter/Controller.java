package com.example.adapter;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private TextField inputField;

    @FXML
    private ListView<String> demolist;

    @FXML
    private Button insertButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button findButton;

    @FXML
    private Rectangle resultIndicator;

    private Adapter demo;

    public Controller() {
        demo = new Adapter();
    }

    @FXML
    public void initialize() {
        updateListView();
    }

    @FXML
    private void handleInsert() {
        String input = inputField.getText();
        if (!input.isEmpty()) {
            demo.insert(Long.parseLong(input));
            updateListView();
            inputField.clear();
        }
    }

    @FXML
    private void handleDelete() {
        String input = inputField.getText();
        if (!input.isEmpty()) {
            demo.delete(Long.parseLong(input));
            updateListView();
            inputField.clear();
        }
    }

    @FXML
    private void handleFind() {
        String input = inputField.getText();
        if (!input.isEmpty()) {
            boolean found = demo.find(Long.parseLong(input)) != demo.display().size();
            if (found) {
                resultIndicator.setFill(javafx.scene.paint.Color.GREEN);
            } else {
                resultIndicator.setFill(javafx.scene.paint.Color.RED);
            }
        }
    }

    private void updateListView() {
        demolist.getItems().clear();
        demolist.getItems().addAll(demo.display());
        resultIndicator.setFill(javafx.scene.paint.Color.TRANSPARENT);
    }
}
