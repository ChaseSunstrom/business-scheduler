package main.java.com.scheduler.UI;

import main.java.com.scheduler.Models.Resource;
import main.java.com.scheduler.Models.Task;
import main.java.com.scheduler.Service.SchedulerService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.util.converter.LocalDateStringConverter;
import java.util.stream.Collectors;
import com.scheduler.Models.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Controller for the main scheduler UI.
 */
public class MainController {
    private final SchedulerService service = new SchedulerService();

    @FXML private TableView<Resource> resourceTable;
    @FXML private TableColumn<Resource, String> resIdCol;
    @FXML private TableColumn<Resource, String> resNameCol;
    @FXML private TableColumn<Resource, String> resTypeCol;
    @FXML private TextField resIdField;
    @FXML private TextField resNameField;
    @FXML private TextField resTypeField;

    @FXML private TableView<Task> taskTable;
    @FXML private TableColumn<Task, String> taskIdCol;
    @FXML private TableColumn<Task, String> taskNameCol;
    @FXML private TableColumn<Task, Number> taskUrgencyCol;
    @FXML private TableColumn<Task, LocalDateTime> taskDeadlineCol;
    @FXML private TableColumn<Task, Resource> taskResourceCol;
    @FXML private TableColumn<Task, TaskStatus> taskStatusCol;
    @FXML private TextField taskIdField;
    @FXML private TextField taskNameField;
    @FXML private TextField taskUrgencyField;
    @FXML private DatePicker taskDeadlinePicker;
    @FXML private ComboBox<Resource> taskResourceCombo;

    @FXML private Button clearFinishedBtn;
    @FXML private Button clearCanceledBtn;

    private final ObservableList<Resource> resourceList = FXCollections.observableArrayList();
    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Resource table setup
        resIdCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getId()));
        resNameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        resTypeCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getType()));
        resourceTable.setItems(resourceList);

        // Context menu for resources
        resourceTable.setRowFactory(table -> {
            TableRow<Resource> row = new TableRow<>();
            ContextMenu menu = new ContextMenu();
            MenuItem editItem = new MenuItem("Edit");
            MenuItem deleteItem = new MenuItem("Delete");
            editItem.setOnAction(e -> loadResourceForEdit(row.getItem()));
            deleteItem.setOnAction(e -> deleteResource(row.getItem()));
            menu.getItems().addAll(editItem, deleteItem);
            row.contextMenuProperty().bind(
                Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(menu)
            );
            return row;
        });

        // Task table setup
        taskIdCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getId()));
        taskNameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        taskUrgencyCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getUrgency()));
        taskDeadlineCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDeadline()));
        taskResourceCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getResource()));
        taskStatusCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStatus()));
        taskStatusCol.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(TaskStatus.values())));
        taskStatusCol.setOnEditCommit(e -> {
            Task t = e.getRowValue();
            t.setStatus(e.getNewValue());
            service.updateTask(t.getId(), t.getName(), t.getUrgency(), t.getDeadline(), t.getResource());
            refreshTasks();
        });
        taskTable.setItems(taskList);
        taskTable.setEditable(true);

        // Context menu for tasks
        taskTable.setRowFactory(table -> {
            TableRow<Task> row = new TableRow<>();
            ContextMenu menu = new ContextMenu();
            MenuItem editItem = new MenuItem("Edit");
            MenuItem deleteItem = new MenuItem("Delete");
            editItem.setOnAction(e -> loadTaskForEdit(row.getItem()));
            deleteItem.setOnAction(e -> deleteTask(row.getItem()));
            menu.getItems().addAll(editItem, deleteItem);
            row.contextMenuProperty().bind(
                Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(menu)
            );
            return row;
        });

        taskResourceCombo.setItems(resourceList);
    }

    @FXML
    private void handleAddResource() {
        try {
            String id = resIdField.getText().trim();
            String name = resNameField.getText().trim();
            String type = resTypeField.getText().trim();
            Resource res = new Resource(id, name, type);
            service.addResource(res);
            resourceList.add(res);
            clearResourceFields();
        } catch (Exception ex) {
            showAlert("Error Adding Resource", ex.getMessage());
        }
    }

    private void clearResourceFields() {
        resIdField.clear();
        resNameField.clear();
        resTypeField.clear();
    }

    private void loadResourceForEdit(Resource res) {
        resIdField.setText(res.getId());
        resNameField.setText(res.getName());
        resTypeField.setText(res.getType());
    }

    private void deleteResource(Resource res) {
        try {
            service.removeResource(res.getId());
            resourceList.remove(res);
        } catch (Exception ex) {
            showAlert("Error Deleting Resource", ex.getMessage());
        }
    }

    @FXML
    private void handleAddTask() {
        try {
            String id = taskIdField.getText().trim();
            String name = taskNameField.getText().trim();
            int urgency = Integer.parseInt(taskUrgencyField.getText().trim());
            LocalDate date = taskDeadlinePicker.getValue();
            if (date == null) throw new IllegalArgumentException("Deadline must be selected");
            LocalDateTime deadline = LocalDateTime.of(date, LocalTime.MIDNIGHT);
            Resource res = taskResourceCombo.getValue();
            if (res == null) throw new IllegalArgumentException("Resource must be selected");
            boolean exists = service.hasTask(id);
            if (exists) {
                service.updateTask(id, name, urgency, deadline, res);
            } else {
                Task task = new Task(id, name, urgency, deadline, res);
                service.addTask(task);
            }
            refreshTasks();
            clearTaskFields();
        } catch (Exception ex) {
            showAlert("Error Adding/Updating Task", ex.getMessage());
        }
    }

    private void refreshTasks() {
        taskList.setAll(service.getPrioritizedTasks());
    }

    private void clearTaskFields() {
        taskIdField.clear();
        taskNameField.clear();
        taskUrgencyField.clear();
        taskDeadlinePicker.setValue(null);
        taskResourceCombo.setValue(null);
    }

    private void loadTaskForEdit(Task t) {
        taskIdField.setText(t.getId());
        taskNameField.setText(t.getName());
        taskUrgencyField.setText(String.valueOf(t.getUrgency()));
        taskDeadlinePicker.setValue(t.getDeadline().toLocalDate());
        taskResourceCombo.setValue(t.getResource());
    }

    private void deleteTask(Task t) {
        try {
            service.removeTask(t.getId());
            refreshTasks();
        } catch (Exception ex) {
            showAlert("Error Deleting Task", ex.getMessage());
        }
    }

    @FXML
    private void handleClearFinished() {
        var toRemove = taskList.stream()
                .filter(t -> t.getStatus() == TaskStatus.FINISHED)
                .collect(Collectors.toList());
        toRemove.forEach(t -> service.removeTask(t.getId()));
        refreshTasks();
    }

    @FXML
    private void handleClearCanceled() {
        var toRemove = taskList.stream()
                .filter(t -> t.getStatus() == TaskStatus.CANCELED)
                .collect(Collectors.toList());
        toRemove.forEach(t -> service.removeTask(t.getId()));
        refreshTasks();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
} 