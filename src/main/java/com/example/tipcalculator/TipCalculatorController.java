package com.example.tipcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TipCalculatorController {

    @FXML
    private TextField sumTextField;

    @FXML
    private Label tipLabel1, tipLabel2, tipLabel3;

    @FXML
    private Button tipButton1, tipButton2, tipButton3;

    private Procent procent;

    @FXML
    public void initialize() {
        tipButton1.setOnAction(e -> calculateTip(15, tipLabel1));
        tipButton2.setOnAction(e -> calculateTip(10, tipLabel2));
        tipButton3.setOnAction(e -> calculateTip(3, tipLabel3));
    }

    private void calculateTip(int percentage, Label resultLabel) {
        try {
            float sum = Float.parseFloat(sumTextField.getText());
            procent = new Procent(sum);
            int total = procent.countSumRnd(percentage);
            resultLabel.setText("Чаевые: " + (int)(total-sum) +" руб. Итого: " + total + " руб.");
        } catch (NumberFormatException e) {
            resultLabel.setText("Ошибка ввода!");
        }
    }
}
