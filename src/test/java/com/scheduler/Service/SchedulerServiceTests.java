package test.java.com.scheduler.Service;

import main.java.com.scheduler.Models.Resource;
import main.java.com.scheduler.Models.Task;
import main.java.com.scheduler.Service.SchedulerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerServiceTests {
    private SchedulerService service;
    private Resource r1;

    @BeforeEach
    void setUp() {
        service = new SchedulerService();
        r1 = new Resource("res1", "Alice", "Person");
        service.addResource(r1);
    }

    @Test
    void testAddGetRemoveResource() {
        Resource fetched = service.getResource("res1");
        assertEquals(r1, fetched);
        service.updateResource("res1", "Bob", "Person");
        Resource updated = service.getResource("res1");
        assertEquals("Bob", updated.getName());
        service.removeResource("res1");
        assertThrows(IllegalArgumentException.class, () -> service.getResource("res1"));
    }

    @Test
    void testAddGetRemoveTaskAndPrioritization() {
        Task t1 = new Task("t1", "Task1", 3, LocalDateTime.now().plusDays(1), r1);
        Task t2 = new Task("t2", "Task2", 5, LocalDateTime.now().plusDays(2), r1);
        service.addTask(t1);
        service.addTask(t2);
        assertEquals(t1, service.getTask("t1"));
        assertEquals(t2, service.getTask("t2"));
        service.updateTask("t1", "Task1Updated", 4, t1.getDeadline(), r1);
        Task updated = service.getTask("t1");
        assertEquals("Task1Updated", updated.getName());
        // Check prioritization: t2 (urgency 5) before t1 (urgency 4)
        List<Task> prioritized = service.getPrioritizedTasks();
        assertEquals(List.of(t2, updated), prioritized);
        service.removeTask("t1");
        assertThrows(IllegalArgumentException.class, () -> service.getTask("t1"));
    }
} 