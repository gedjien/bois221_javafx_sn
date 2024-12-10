package com.example.strategy;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Random;

public class Controller {

    @FXML
    private ComboBox<String> sortingComboBox;
    @FXML
    private TextField unsortedArray;
    @FXML
    private TextField sortedArray;

    private int[] array;
    private CurrentContext context;

    @FXML
    public void initialize() {
        sortingComboBox.getItems().addAll("Bubble Sort", "Selection Sort");
        sortingComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void fillArray() {
        Random random = new Random();
        array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        unsortedArray.setText(arrayToString(array));
        sortedArray.clear();
    }

    @FXML
    public void sortArray() {
        String selectedStrategy = sortingComboBox.getSelectionModel().getSelectedItem();
        switch (selectedStrategy) {
            case "Bubble Sort":
                context = new CurrentContext(new BubbleSort());
                break;
            case "Selection Sort":
                context = new CurrentContext(new SelectionSort());
                break;
        }
        context.sortNumbers(array);
        sortedArray.setText(arrayToString(array));
    }

    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int num : array) {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
    }
}
