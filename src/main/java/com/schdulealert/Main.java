package com.schdulealert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;       // Для использования List
import java.io.IOException;  // Для использования IOException

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Загружаем FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScheduleApp.fxml"));
            Parent root = loader.load();

            // Создаем сцену
            Scene scene = new Scene(root);
            primaryStage.setTitle("Сколько до пары");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
