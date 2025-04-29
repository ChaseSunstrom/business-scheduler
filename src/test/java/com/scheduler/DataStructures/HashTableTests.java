package test.java.com.scheduler.DataStructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.com.scheduler.DataStructures.HashTable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTests {
    private HashTable<String, Integer> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
    }

    @Test
    void testPutAndGet() {
        table.put("a", 1);
        table.put("b", 2);
        assertEquals(1, table.get("a"));
        assertEquals(2, table.get("b"));
    }

    @Test
    void testUpdateValue() {
        table.put("key", 10);
        table.put("key", 20);
        assertEquals(20, table.get("key"));
        assertEquals(1, table.size());
    }

    @Test
    void testRemove() {
        table.put("x", 100);
        Integer removed = table.remove("x");
        assertEquals(100, removed);
        assertNull(table.get("x"));
        assertEquals(0, table.size());
    }

    @Test
    void testContainsKey() {
        table.put("z", 5);
        assertTrue(table.containsKey("z"));
        assertFalse(table.containsKey("y"));
    }

    @Test
    void testKeysList() {
        table.put("one", 1);
        table.put("two", 2);
        List<String> keys = table.keys();
        assertTrue(keys.contains("one"));
        assertTrue(keys.contains("two"));
        assertEquals(2, keys.size());
    }

    @Test
    void testNullKeyThrows() {
        assertThrows(NullPointerException.class, () -> table.put(null, 1));
        assertThrows(NullPointerException.class, () -> table.get(null));
        assertThrows(NullPointerException.class, () -> table.remove(null));
    }
} 