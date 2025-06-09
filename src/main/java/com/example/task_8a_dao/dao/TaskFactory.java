package com.example.task_8a_dao.dao;

public class TaskFactory {
    public static final String JSON = "JSON";
    public static final String FILE = "Файл";
    public static final String RAM = "Память";

    public static TaskDAO createTaskDAO(String type) {
        if (type.equalsIgnoreCase(JSON)) {
            return new JsonTaskDAO("tasks.json");
        } else if (type.equalsIgnoreCase(FILE)) {
            return new FileTaskDAO("tasks.txt");
        } else if (type.equalsIgnoreCase(RAM)) {
            return new ListTaskDAO(10);
        } else {
            throw new IllegalArgumentException("Неверный тип хранилища: " + type);
        }
    }
} 