package com.example.obs_task7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Главный класс, который запускает приложение JavaFX.
//
//Загружает FXML файл и отображает главное окно приложения.

public class ObserverApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/obs_task7/observer.fxml"));
        primaryStage.setTitle("Observer App");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
