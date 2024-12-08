package com.example.slideshowiterator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.List;

public class SlideShowController {

    @FXML
    private ImageView imageView;

    @FXML
    private Button startButton, stopButton, chooseFolderButton, nextButton, prevButton;

    private ConcreteAggregate slides;
    private List<File> imagesList;
    private int currentImageIndex = 0;
    private Timeline timeline;

    @FXML
    public void initialize() {
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(500); // Настройка размера изображения, чтобы оно не перекрывало кнопки

        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> nextImage()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Бесконечный цикл
    }

    @FXML
    public void chooseFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            slides = new ConcreteAggregate(selectedDirectory.getAbsolutePath());
            imagesList = slides.getImagesList(); // Получаем список изображений
            if (!imagesList.isEmpty()) {
                currentImageIndex = 0;
                updateImage();
            }
        }
    }

    @FXML
    public void startSlideShow() {
        timeline.play();
    }

    @FXML
    public void stopSlideShow() {
        timeline.stop();
    }

    @FXML
    public void nextImage() {
        if (imagesList != null && !imagesList.isEmpty()) {
            currentImageIndex = (currentImageIndex + 1) % imagesList.size();
            updateImage();
        }
    }

    @FXML
    public void prevImage() {
        if (imagesList != null && !imagesList.isEmpty()) {
            currentImageIndex = (currentImageIndex - 1 + imagesList.size()) % imagesList.size();
            updateImage();
        }
    }

    private void updateImage() {
        if (imagesList != null && !imagesList.isEmpty()) {
            File file = imagesList.get(currentImageIndex);
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
    }
}
