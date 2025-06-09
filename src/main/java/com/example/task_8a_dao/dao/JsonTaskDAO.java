package com.example.task_8a_dao.dao;

import com.example.task_8a_dao.model.Task;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonTaskDAO implements TaskDAO {
    private final String filename;
    private List<Task> tasks;

    public JsonTaskDAO(String filename) {
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
                    // Удаляем начальные и конечные скобки
                    content = content.trim();
                    if (content.startsWith("[") && content.endsWith("]")) {
                        content = content.substring(1, content.length() - 1);
                    }
                    
                    // Разбиваем на отдельные объекты
                    String[] taskStrings = content.split("\\},\\s*\\{");
                    for (String taskStr : taskStrings) {
                        // Добавляем скобки обратно, если их нет
                        if (!taskStr.startsWith("{")) taskStr = "{" + taskStr;
                        if (!taskStr.endsWith("}")) taskStr = taskStr + "}";
                        
                        // Извлекаем значения
                        int id = extractInt(taskStr, "id");
                        String name = extractString(taskStr, "name");
                        String time = extractString(taskStr, "time");
                        String context = extractString(taskStr, "context");
                        String status = extractString(taskStr, "status");
                        
                        tasks.add(new Task(id, name, time, context, status));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка загрузки задач из файла: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int extractInt(String json, String key) {
        String pattern = "\"" + key + "\"\\s*:\\s*(\\d+)";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(json);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }

    private String extractString(String json, String key) {
        String pattern = "\"" + key + "\"\\s*:\\s*\"([^\"]*)\"";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(json);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    private void saveTasks() {
        try {
            StringBuilder json = new StringBuilder();
            json.append("[\n");
            
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                json.append("    {\n");
                json.append("        \"id\": ").append(task.getId()).append(",\n");
                json.append("        \"name\": \"").append(task.getName()).append("\",\n");
                json.append("        \"time\": \"").append(task.getTime()).append("\",\n");
                json.append("        \"context\": \"").append(task.getContext()).append("\",\n");
                json.append("        \"status\": \"").append(task.getStatus()).append("\"\n");
                json.append("    }");
                if (i < tasks.size() - 1) {
                    json.append(",");
                }
                json.append("\n");
            }
            
            json.append("]");
            
            Files.writeString(Paths.get(filename), json.toString());
        } catch (IOException e) {
            System.err.println("Ошибка сохранения задач в файл: " + e.getMessage());
            e.printStackTrace();
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