module com.example.slideshowiterator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.slideshowiterator to javafx.fxml;
    exports com.example.slideshowiterator;
}