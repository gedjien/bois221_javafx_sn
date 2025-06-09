package com.example.task_8a_dao.dao;

import com.example.task_8a_dao.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListTaskDAO implements TaskDAO {
    private List<Task> tasks;
    private final String[] statuses = {"В процессе", "Завершено", "Не начато"};
    private final String[] contexts = {
        "Разработка нового функционала",
        "Исправление ошибок",
        "Оптимизация производительности",
        "Тестирование",
        "Документирование",
        "Код ревью",
        "Встреча с командой",
        "Планирование спринта",
        "Обучение новых сотрудников",
        "Обновление документации"
    };

    public ListTaskDAO(int size) {
        tasks = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            Task task = new Task(
                i + 1,
                "Задача " + (i + 1),
                randomTime(random),
                contexts[random.nextInt(contexts.length)],
                statuses[random.nextInt(statuses.length)]
            );
            tasks.add(task);
        }
    }

    private String randomTime(Random random) {
        int hours = random.nextInt(24);
        int minutes = random.nextInt(60);
        return String.format("%02d:%02d", hours, minutes);
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
    }

    @Override
    public void updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                return;
            }
        }
    }

    @Override
    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }
} 