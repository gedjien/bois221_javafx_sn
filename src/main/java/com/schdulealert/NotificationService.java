package com.schdulealert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationService {

    private List<Schedule> scheduleList;
    private UpdateCallback updateCallback;
    private long notificationInterval = 5 * 60 * 1000; // 5 минут по умолчанию
    private Timer timer;
    private TimerTask notificationTask;
    private boolean isPaused = false; // Флаг для отслеживания состояния паузы

    public NotificationService(List<Schedule> scheduleList, UpdateCallback updateCallback) {
        this.scheduleList = scheduleList;
        this.updateCallback = updateCallback;
        this.timer = new Timer();
    }

    public void checkNextPair() {
        LocalDateTime currentTime = LocalDateTime.now();
        Schedule nextPair = null;
        long timeToStart = Long.MAX_VALUE;
        long timeToEnd = Long.MAX_VALUE;

        // Получаем текущую дату, чтобы добавить её к времени
        LocalDateTime today = currentTime.toLocalDate().atStartOfDay();

        for (Schedule schedule : scheduleList) {
            // Преобразуем строки времени в LocalDateTime, добавляем текущую дату
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            // Добавляем ведущий ноль, если время записано в формате "H:mm"
            String startTimeString = schedule.getStartTime();
            if (startTimeString.length() == 4) {
                startTimeString = "0" + startTimeString;  // Добавляем ведущий ноль
            }
            LocalDateTime startTime = today.plusHours(Integer.parseInt(startTimeString.substring(0, 2)))
                    .plusMinutes(Integer.parseInt(startTimeString.substring(3, 5)));

            String endTimeString = schedule.getEndTime();
            if (endTimeString.length() == 4) {
                endTimeString = "0" + endTimeString;  // Добавляем ведущий ноль
            }
            LocalDateTime endTime = today.plusHours(Integer.parseInt(endTimeString.substring(0, 2)))
                    .plusMinutes(Integer.parseInt(endTimeString.substring(3, 5)));

            // Если текущее время до начала пары
            if (currentTime.isBefore(startTime)) {
                long timeUntilStart = currentTime.until(startTime, java.time.temporal.ChronoUnit.MINUTES);
                if (timeUntilStart < timeToStart) {
                    timeToStart = timeUntilStart;
                    nextPair = schedule;
                }
            }

            // Если пара уже идет (текущее время между startTime и endTime)
            if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
                long timeUntilEnd = currentTime.until(endTime, java.time.temporal.ChronoUnit.MINUTES);
                if (timeUntilEnd < timeToEnd) {
                    timeToEnd = timeUntilEnd;
                    nextPair = schedule;
                }
            }
        }

        if (nextPair != null) {
            String subject = nextPair.getSubject();
            String time = nextPair.getStartTime() + " - " + nextPair.getEndTime();
            String timeRemaining = timeToStart < 0 ? timeToEnd + " минут" : timeToStart + " минут";

            // Вызываем обратный вызов для обновления UI
            if (updateCallback != null) {
                updateCallback.onUpdate(subject, time, timeRemaining);
            }
        } else {
            if (updateCallback != null) {
                updateCallback.onUpdate("Следующая пара не определена", "00:00 - 00:00", "0 минут");
            }
        }
    }

    public void startNotifications() {
        // Остановка существующего задания перед запуском нового
        if (notificationTask != null) {
            notificationTask.cancel();
        }
        notificationTask = new TimerTask() {
            @Override
            public void run() {
                checkNextPair();
            }
        };
        timer.scheduleAtFixedRate(notificationTask, 0, notificationInterval);
        System.out.println("Уведомления запущены с интервалом: " + (notificationInterval / 1000 / 60) + " минут.");
    }


    public void stopNotifications() {
        // Остановка уведомлений
        if (notificationTask != null) {
            notificationTask.cancel();
            isPaused = false; // Останавливаем, сбрасывая флаг
        }
    }

    public void pauseNotifications() {
        // Приостановить уведомления
        if (notificationTask != null) {
            notificationTask.cancel();
            isPaused = true; // Устанавливаем флаг паузы
        }
    }

    public void resumeNotifications() {
        // Возобновить уведомления
        if (isPaused) {
            startNotifications();  // Перезапускаем уведомления, если они были приостановлены
            isPaused = false; // Сбрасываем флаг паузы
        }
    }

    public void setNotificationInterval(long minutes) {
        this.notificationInterval = minutes * 60 * 1000; // переводим в миллисекунды
    }
}
