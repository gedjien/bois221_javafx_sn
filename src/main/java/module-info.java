module com.example.tipcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.tipcalculator to javafx.fxml;
    exports com.example.tipcalculator;
}