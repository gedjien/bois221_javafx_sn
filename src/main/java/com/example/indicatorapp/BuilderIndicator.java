package com.example.indicatorapp;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BuilderIndicator implements Builder {

    private Indicator indicator = new Indicator();
    private float start, stop;

    @Override
    public void setView(int N, char norm, char select) {
        // Реализуйте метод для настройки индикатора
    }

    @Override
    public void lineBounds(float start, float stop) {
        this.start = start;
        this.stop = stop;
        FlowPane pane = new FlowPane();

        Text textStart = new Text(String.valueOf(start));
        Line line = new Line(5, 5, 100, 5);
        Text textStop = new Text(String.valueOf(stop));

        pane.getChildren().add(textStart);
        pane.getChildren().add(line);
        pane.getChildren().add(textStop);

        indicator.add(pane);
    }

    @Override
    public void linePaint(float measure) {
        AnchorPane pane = new AnchorPane();
        double x = pane.getWidth() * measure / (stop - start);

        Arc arc = new Arc();
        arc.setFill(Color.RED);
        arc.setCenterX(x);
        arc.setCenterY(10);
        arc.setRadiusX(20);
        arc.setRadiusY(25);
        arc.setLength(100);
        arc.setStartAngle(30);

        pane.getChildren().add(arc);
        indicator.add(pane);
    }

    @Override
    public void lineMark(String measure) {
        AnchorPane pane = new AnchorPane();
        Text text = new Text(measure);
        pane.getChildren().add(text);
        indicator.add(pane);
    }

    @Override
    public void addTitle(String name) {
        // Реализуйте метод для добавления заголовка
    }

    @Override
    public Indicator build() {
        return indicator;
    }
}
