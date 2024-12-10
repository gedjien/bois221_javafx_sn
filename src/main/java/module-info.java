module com.example.memento {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.memento to javafx.fxml;
    exports com.example.memento;
}