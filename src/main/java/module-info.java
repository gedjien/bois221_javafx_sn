module com.example.task_8a_dao {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Для работы приложения
    opens com.example.task_8a_dao to javafx.fxml;
    opens com.example.task_8a_dao.model to javafx.base;
    exports com.example.task_8a_dao;
    exports com.example.task_8a_dao.model;
}