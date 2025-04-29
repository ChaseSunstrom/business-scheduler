package main.java.com.scheduler.Algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for merge sort algorithm.
 */
public class MergeSort {
    /**
     * Sorts the given list in place using merge sort.
     * @param list list of Comparable elements
     * @param <T> type of elements
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        if (list == null || list.size() < 2) return;
        List<T> aux = new ArrayList<>(list);
        mergeSort(aux, list, 0, list.size() - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSort(List<T> src, List<T> dest, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid);
        mergeSort(dest, src, mid + 1, high);
        merge(src, dest, low, mid, high);
    }

    private static <T extends Comparable<? super T>> void merge(List<T> src, List<T> dest, int low, int mid, int high) {
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) dest.set(k, src.get(j++));
            else if (j > high) dest.set(k, src.get(i++));
            else if (src.get(i).compareTo(src.get(j)) <= 0) dest.set(k, src.get(i++));
            else dest.set(k, src.get(j++));
        }
    }
} 