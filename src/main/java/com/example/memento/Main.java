package com.example.memento;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загрузка FXML-файла
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/memento/editor-layout.fxml"));
        AnchorPane root = loader.load();

        // Получаем доступ к контроллеру и передаем сцену
        Controller controller = loader.getController();

        // Настройка сцены и запуск
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Графический редактор");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
