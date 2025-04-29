package main.java.com.scheduler.Service;

import main.java.com.scheduler.Models.Resource;
import main.java.com.scheduler.DataStructures.HashTable;
import main.java.com.scheduler.DataStructures.BinarySearchTree;
import main.java.com.scheduler.Algorithms.MergeSort;
import main.java.com.scheduler.Models.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for managing resources and tasks.
 */
public class SchedulerService {
    private final HashTable<String, Resource> resources = new HashTable<>();
    private final BinarySearchTree<String, Task> tasks = new BinarySearchTree<>();

    public void addResource(Resource resource) {
        if (resources.containsKey(resource.getId())) {
            throw new IllegalArgumentException("Resource ID already exists: " + resource.getId());
        }
        resources.put(resource.getId(), resource);
    }

    public Resource getResource(String id) {
        Resource r = resources.get(id);
        if (r == null) {
            throw new IllegalArgumentException("No resource with id: " + id);
        }
        return r;
    }

    public void updateResource(String id, String name, String type) {
        Resource r = getResource(id);
        r.setName(name);
        r.setType(type);
    }

    public void removeResource(String id) {
        Resource removed = resources.remove(id);
        if (removed == null) {
            throw new IllegalArgumentException("No resource with id: " + id);
        }
    }

    public List<Resource> getAllResources() {
        List<Resource> list = new ArrayList<>();
        for (String key : resources.keys()) {
            list.add(resources.get(key));
        }
        return list;
    }

    public void addTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            throw new IllegalArgumentException("Task ID already exists: " + task.getId());
        }
        tasks.put(task.getId(), task);
    }

    public Task getTask(String id) {
        Task t = tasks.get(id);
        if (t == null) {
            throw new IllegalArgumentException("No task with id: " + id);
        }
        return t;
    }

    public void updateTask(String id, String name, int urgency, LocalDateTime deadline, Resource resource) {
        Task t = getTask(id);
        t.setName(name);
        t.setUrgency(urgency);
        t.setDeadline(deadline);
        t.setResource(resource);
    }

    public void removeTask(String id) {
        Task removed = tasks.remove(id);
        if (removed == null) {
            throw new IllegalArgumentException("No task with id: " + id);
        }
    }

    public List<Task> getAllTasks() {
        return tasks.values();
    }

    public List<Task> getPrioritizedTasks() {
        List<Task> list = new ArrayList<>(tasks.values());
        MergeSort.sort(list);
        return list;
    }

    /**
     * Checks if a resource with the given id exists.
     * @param id resource id
     * @return true if exists, false otherwise
     */
    public boolean hasResource(String id) {
        return resources.containsKey(id);
    }

    /**
     * Checks if a task with the given id exists.
     * @param id task id
     * @return true if exists, false otherwise
     */
    public boolean hasTask(String id) {
        return tasks.containsKey(id);
    }
} 