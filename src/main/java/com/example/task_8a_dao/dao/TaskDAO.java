package com.example.task_8a_dao.dao;

import com.example.task_8a_dao.model.Task;
import java.util.List;

public interface TaskDAO {
    List<Task> getAllTasks();
    Task getTaskById(int id);
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(int id);
} 