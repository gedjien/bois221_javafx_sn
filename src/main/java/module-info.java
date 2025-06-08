module com.example.game_of_bean_bags {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics; // Добавьте эту строку

    opens com.example.game_of_bean_bags to javafx.fxml;
    exports com.example.game_of_bean_bags;
}