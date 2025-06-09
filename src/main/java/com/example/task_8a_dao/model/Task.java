package com.example.task_8a_dao.model;

public class Task {
    private int id;
    private String name;
    private String time;
    private String status;
    private String context;

    public Task(int id, String name, String time, String context, String status) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.context = context;
        this.status = status;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getTime() { return time; }
    public String getStatus() { return status; }
    public String getContext() { return context; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTime(String time) { this.time = time; }
    public void setStatus(String status) { this.status = status; }
    public void setContext(String context) { this.context = context; }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
} 