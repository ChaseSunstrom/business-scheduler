package test.java.com.scheduler.Algorithms;

import org.junit.jupiter.api.Test;
import main.java.com.scheduler.Algorithms.MergeSort;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTests {
    @Test
    void testEmptyList() {
        List<Integer> list = new ArrayList<>();
        MergeSort.sort(list);
        assertTrue(list.isEmpty());
    }

    @Test
    void testSingleElement() {
        List<Integer> list = new ArrayList<>(List.of(5));
        MergeSort.sort(list);
        assertEquals(List.of(5), list);
    }

    @Test
    void testMultipleElements() {
        List<Integer> list = new ArrayList<>(List.of(3, 1, 4, 1, 5, 9, 2));
        MergeSort.sort(list);
        assertEquals(List.of(1, 1, 2, 3, 4, 5, 9), list);
    }

    @Test
    void testAlreadySorted() {
        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        MergeSort.sort(list);
        assertEquals(List.of("a", "b", "c"), list);
    }

    @Test
    void testReverseSorted() {
        List<String> list = new ArrayList<>(List.of("c", "b", "a"));
        MergeSort.sort(list);
        assertEquals(List.of("a", "b", "c"), list);
    }
} 