package com.example.strategy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StrategyApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/strategy/Main.fxml"));
        primaryStage.setTitle("Strategy Pattern Example");
        primaryStage.setScene(new Scene(root, 600, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
