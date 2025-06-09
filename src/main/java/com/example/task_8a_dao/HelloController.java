package com.example.task_8a_dao;

import com.example.task_8a_dao.dao.TaskDAO;
import com.example.task_8a_dao.dao.TaskFactory;
import com.example.task_8a_dao.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML private ComboBox<String> storageTypeComboBox;
    @FXML private TableView<Task> taskTableView;
    @FXML private TableColumn<Task, Integer> idColumn;
    @FXML private TableColumn<Task, String> nameColumn;
    @FXML private TableColumn<Task, String> timeColumn;
    @FXML private TableColumn<Task, String> contextColumn;
    @FXML private TableColumn<Task, String> statusColumn;
    @FXML private TextField nameField;
    @FXML private TextField timeField;
    @FXML private TextField contextField;
    @FXML private ComboBox<String> statusComboBox;

    private TaskDAO taskDAO;
    private ObservableList<Task> tasks;

    @FXML
    public void initialize() {
        // Initialize storage type combo box
        storageTypeComboBox.getItems().addAll(
            TaskFactory.JSON,
            TaskFactory.FILE,
            TaskFactory.RAM
        );

        // Initialize status combo box
        statusComboBox.getItems().addAll(
            "В процессе",
            "Завершено",
            "Ожидает"
        );

        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        contextColumn.setCellValueFactory(new PropertyValueFactory<>("context"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Set column titles
        idColumn.setText("ID");
        nameColumn.setText("Название");
        timeColumn.setText("Время");
        contextColumn.setText("Контекст");
        statusColumn.setText("Статус");

        // Initialize tasks list
        tasks = FXCollections.observableArrayList();
        taskTableView.setItems(tasks);

        // Add selection listener
        taskTableView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    nameField.setText(newValue.getName());
                    timeField.setText(newValue.getTime());
                    contextField.setText(newValue.getContext());
                    statusComboBox.setValue(newValue.getStatus());
                }
            }
        );
    }

    @FXML
    private void handleLoad() {
        String selectedType = storageTypeComboBox.getValue();
        if (selectedType != null) {
            taskDAO = TaskFactory.createTaskDAO(selectedType);
            refreshTasks();
        }
    }

    @FXML
    private void handleAdd() {
        if (taskDAO == null) {
            showAlert("Ошибка", "Пожалуйста, выберите тип хранилища");
            return;
        }

        Task task = new Task(
            0, // ID будет установлен DAO
            nameField.getText(),
            timeField.getText(),
            contextField.getText(),
            statusComboBox.getValue()
        );

        taskDAO.addTask(task);
        refreshTasks();
        clearFields();
    }

    @FXML
    private void handleUpdate() {
        Task selectedTask = taskTableView.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            showAlert("Ошибка", "Пожалуйста, выберите задачу для обновления");
            return;
        }

        selectedTask.setName(nameField.getText());
        selectedTask.setTime(timeField.getText());
        selectedTask.setContext(contextField.getText());
        selectedTask.setStatus(statusComboBox.getValue());

        taskDAO.updateTask(selectedTask);
        refreshTasks();
    }

    @FXML
    private void handleDelete() {
        Task selectedTask = taskTableView.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            showAlert("Ошибка", "Пожалуйста, выберите задачу для удаления");
            return;
        }

        taskDAO.deleteTask(selectedTask.getId());
        refreshTasks();
        clearFields();
    }

    private void refreshTasks() {
        tasks.clear();
        tasks.addAll(taskDAO.getAllTasks());
    }

    private void clearFields() {
        nameField.clear();
        timeField.clear();
        contextField.clear();
        statusComboBox.setValue(null);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}