package com.example.decorator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements javafx.fxml.Initializable {

    @FXML
    private Pane paintPane;
    @FXML
    private CheckBox checkBoxStar;
    @FXML
    private CheckBox checkBoxGarland;
    @FXML
    private CheckBox checkBoxPresents;
    @FXML
    private Button addAllButton;
    @FXML
    private Button removeAllButton;

    private ChristmasTree tree;
    private ChristmasTree dtree;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tree = new ChristmasTreeImpl();
        dtree = tree;
        paintPane.getChildren().clear();
        dtree.Operation(paintPane);
    }

    @FXML
    public void decorateTree() {
        paintPane.getChildren().clear();
        dtree = tree;

        if (checkBoxStar.isSelected()) {
            dtree = new StarDecorator(dtree);
        }
        if (checkBoxGarland.isSelected()) {
            dtree = new GarlandDecorator(dtree);
        }
        if (checkBoxPresents.isSelected()) {
            dtree = new PresentsDecorator(dtree);
        }

        dtree.Operation(paintPane);
    }

    @FXML
    public void addAll() {
        checkBoxStar.setSelected(true);
        checkBoxGarland.setSelected(true);
        checkBoxPresents.setSelected(true);
        decorateTree();
    }

    @FXML
    public void removeAll() {
        checkBoxStar.setSelected(false);
        checkBoxGarland.setSelected(false);
        checkBoxPresents.setSelected(false);
        paintPane.getChildren().clear();
        tree.Operation(paintPane);
    }
}
