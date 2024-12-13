package com.schdulealert;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    @FXML
    private Label scheduleLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label timeRemainingLabel;

    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resumeButton;
    @FXML
    private Button loadFileButton;

    @FXML
    private TextField intervalTextField;

    private NotificationService notificationService;

    @FXML
    public void initialize() {
        // Инициализация интерфейса, например, назначение обработчиков событий для кнопок
        pauseButton.setOnAction(event -> pauseNotifications());
        resumeButton.setOnAction(event -> resumeNotifications());
    }

    public void updateUI(String scheduleText, String timeText, String timeRemainingText) {
        Platform.runLater(() -> {
            scheduleLabel.setText("Следующая пара: " + scheduleText);
            timeLabel.setText("Время: " + timeText);
            timeRemainingLabel.setText("До начала: " + timeRemainingText);

            // Выводим информацию в консоль
            System.out.println("Следующая пара: " + scheduleText);
            System.out.println("Время: " + timeText);
            System.out.println("До начала: " + timeRemainingText);
        });
    }


    @FXML
    public void setNotificationInterval() {
        try {
            long interval = Long.parseLong(intervalTextField.getText());
            notificationService.setNotificationInterval(interval);
            // Перезапускаем уведомления с новым интервалом
            notificationService.stopNotifications();
            notificationService.startNotifications();
            System.out.println("Интервал уведомлений установлен на " + interval + " минут.");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат интервала.");
        }
    }


    @FXML
    public void startNotifications() {
        notificationService.startNotifications();
    }

    @FXML
    public void stopNotifications() {
        System.out.println("Уведомления остановлены.");
        notificationService.stopNotifications();
    }

    @FXML
    public void pauseNotifications() {
        System.out.println("Уведомления установлены на паузу.");
        notificationService.pauseNotifications();
    }

    @FXML
    public void resumeNotifications() {
        System.out.println("Уведомления возобновлены.");
        notificationService.resumeNotifications();
    }

    @FXML
    public void loadScheduleFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                List<Schedule> scheduleList = ScheduleReader.readSchedule(selectedFile.getAbsolutePath());
                notificationService = new NotificationService(scheduleList, this::updateUI);
                System.out.println("Расписание успешно загружено из файла: " + selectedFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка при загрузке файла.");
            }
        } else {
            System.out.println("Файл не выбран.");
        }
    }
}
