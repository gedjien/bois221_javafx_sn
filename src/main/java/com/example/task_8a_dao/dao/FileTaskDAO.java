package com.example.task_8a_dao.dao;

import com.example.task_8a_dao.model.Task;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileTaskDAO implements TaskDAO {
    private final String filename;
    private List<Task> tasks;

    public FileTaskDAO(String filename) {
        this.filename = "src/main/resources/" + filename;
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    private void loadTasks() {
        try {
            Path filePath = Paths.get(filename);
            if (Files.exists(filePath)) {
                String content = Files.readString(filePath);
                if (!content.trim().isEmpty()) {
                    String[] lines = content.split("\n");
                    for (String line : lines) {
                        if (!line.trim().isEmpty()) {
                            String[] parts = line.split(",");
                            if (parts.length >= 5) {
                                Task task = new Task(
                                    Integer.parseInt(parts[0]),
                                    parts[1],
                                    parts[2],
                                    parts[3],
                                    parts[4]
                                );
                                tasks.add(task);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка загрузки задач из файла: " + e.getMessage());
        }
    }

    private void saveTasks() {
        try {
            StringBuilder content = new StringBuilder();
            for (Task task : tasks) {
                content.append(String.format("%d,%s,%s,%s,%s\n",
                    task.getId(),
                    task.getName(),
                    task.getTime(),
                    task.getContext(),
                    task.getStatus()));
            }
            Files.writeString(Paths.get(filename), content.toString());
        } catch (IOException e) {
            System.err.println("Ошибка сохранения задач в файл: " + e.getMessage());
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void addTask(Task task) {
        int maxId = tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0);
        task.setId(maxId + 1);
        
        tasks.add(task);
        saveTasks();
    }

    @Override
    public void updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                saveTasks();
                return;
            }
        }
    }

    @Override
    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
        saveTasks();
    }
} 