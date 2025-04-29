package main.java.com.scheduler.Models;

import java.time.LocalDateTime;
import java.util.Objects;
import com.scheduler.Models.TaskStatus;

/**
 * Represents a task with id, name, urgency, deadline, resource, Comparable implementation and validation
 */
public class Task implements Comparable<Task> {
    private final String id;
    private String name;
    private int urgency; // 1 (lowest) to 5 (highest)
    private LocalDateTime deadline;
    private Resource resource;
    private TaskStatus status;

    /**
     * Constructs a Task.
     * @param id unique identifier, non-null/non-empty
     * @param name task name, non-null/non-empty
     * @param urgency urgency level (1-5)
     * @param deadline non-null deadline
     * @param resource assigned resource, non-null
     */
    public Task(String id, String name, int urgency, LocalDateTime deadline, Resource resource) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("Task id cannot be null or empty");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Task name cannot be null or empty");
        if (urgency < 1 || urgency > 5) throw new IllegalArgumentException("Urgency must be between 1 and 5");
        if (deadline == null) throw new IllegalArgumentException("Deadline cannot be null");
        if (resource == null) throw new IllegalArgumentException("Resource cannot be null");
        this.id = id;
        this.name = name;
        this.urgency = urgency;
        this.deadline = deadline;
        this.resource = resource;
        this.status = TaskStatus.PENDING;
    }

    /**
     * Returns the status of this task.
     */
    public TaskStatus getStatus() { return status; }

    /**
     * Sets the status of this task.
     * @param status new status, non-null
     */
    public void setStatus(TaskStatus status) {
        if (status == null) throw new IllegalArgumentException("Status cannot be null");
        this.status = status;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getUrgency() { return urgency; }
    public LocalDateTime getDeadline() { return deadline; }
    public Resource getResource() { return resource; }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Task name cannot be null or empty");
        this.name = name;
    }

    public void setUrgency(int urgency) {
        if (urgency < 1 || urgency > 5) throw new IllegalArgumentException("Urgency must be between 1 and 5");
        this.urgency = urgency;
    }

    public void setDeadline(LocalDateTime deadline) {
        if (deadline == null) throw new IllegalArgumentException("Deadline cannot be null");
        this.deadline = deadline;
    }

    public void setResource(Resource resource) {
        if (resource == null) throw new IllegalArgumentException("Resource cannot be null");
        this.resource = resource;
    }

    @Override
    public int compareTo(Task other) {
        // Higher urgency first, then earlier deadline
        int cmp = Integer.compare(other.urgency, this.urgency);
        if (cmp != 0) return cmp;
        return this.deadline.compareTo(other.deadline);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (Urgency: %d, Deadline: %s, Status: %s) - %s", id, name, urgency, deadline, status, resource);
    }
} 