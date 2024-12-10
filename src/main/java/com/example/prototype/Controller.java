package com.example.prototype;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements javafx.fxml.Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private ListView<Shape> listView;

    @FXML
    private ColorPicker colorPicker;

    private ObservableList<Shape> content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Circle circle = new Circle();
        Rectangle rectangle = new Rectangle();
        Triangle triangle = new Triangle();
        Square square = new Square();

        content = FXCollections.observableArrayList(circle, square, rectangle, triangle);
        listView.setItems(content);
        listView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
    }

    @FXML
    public void drawShape(MouseEvent mouseEvent) {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Shape shape = (Shape) content.get(index).clone();
            shape.setColor(colorPicker.getValue());
            shape.setXY(mouseEvent.getX(), mouseEvent.getY());
            shape.draw(canvas.getGraphicsContext2D(), mouseEvent.getX(), mouseEvent.getY());
        }
    }

    @FXML
    public void clearCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
