package test.java.com.scheduler.DataStructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.com.scheduler.DataStructures.BinarySearchTree;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTests {
    private BinarySearchTree<Integer, String> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    void testEmptyTree() {
        assertNull(bst.get(1));
        assertFalse(bst.containsKey(1));
        assertEquals(0, bst.size());
        assertTrue(bst.values().isEmpty());
    }

    @Test
    void testPutAndGet() {
        bst.put(5, "five");
        bst.put(3, "three");
        bst.put(7, "seven");
        assertEquals("five", bst.get(5));
        assertEquals("three", bst.get(3));
        assertEquals("seven", bst.get(7));
    }

    @Test
    void testUpdateValue() {
        bst.put(2, "two");
        bst.put(2, "deux");
        assertEquals(1, bst.size());
        assertEquals("deux", bst.get(2));
    }

    @Test
    void testRemoveLeaf() {
        bst.put(1, "one");
        assertEquals("one", bst.remove(1));
        assertNull(bst.get(1));
        assertEquals(0, bst.size());
    }

    @Test
    void testRemoveNodeWithChildren() {
        bst.put(10, "ten");
        bst.put(5, "five");
        bst.put(15, "fifteen");
        bst.put(12, "twelve");
        assertEquals("ten", bst.remove(10));
        assertFalse(bst.containsKey(10));
        assertEquals(3, bst.size());
    }

    @Test
    void testContainsKey() {
        bst.put(6, "six");
        assertTrue(bst.containsKey(6));
        assertFalse(bst.containsKey(4));
    }

    @Test
    void testValuesInOrder() {
        bst.put(2, "b");
        bst.put(1, "a");
        bst.put(3, "c");
        List<String> vals = bst.values();
        assertEquals(List.of("a", "b", "c"), vals);
    }

    @Test
    void testNullKeyThrows() {
        assertThrows(NullPointerException.class, () -> bst.put(null, "x"));
        assertThrows(NullPointerException.class, () -> bst.get(null));
        assertThrows(NullPointerException.class, () -> bst.remove(null));
    }
} 