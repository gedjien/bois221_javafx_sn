package com.schdulealert;

public class Schedule {

    private String subject;    // Название предмета
    private String startTime;  // Время начала
    private String endTime;    // Время окончания
    private String dayOfWeek;  // День недели

    // Конструктор для первого формата (без дня недели)
    public Schedule(String subject, String startTime, String endTime) {
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = ""; // Без дня недели
    }

    // Конструктор для второго формата (с днем недели)
    public Schedule(String subject, String startTime, String endTime, String dayOfWeek) {
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    // Геттеры и сеттеры
    public String getSubject() {
        return subject;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "subject='" + subject + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                '}';
    }
}
