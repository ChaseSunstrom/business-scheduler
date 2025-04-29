package main.java.com.scheduler.DataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * A simple generic hash table implementation using separate chaining.
 * @param <K> key type
 * @param <V> value type
 */
public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private List<LinkedList<Entry<K, V>>> buckets;
    private int size;

    /**
     * Represents a key-value pair.
     */
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Constructs a hash table with default capacity.
     */
    public HashTable() {
        buckets = new ArrayList<>(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

    private int index(K key) {
        return (Objects.hashCode(key) & 0x7fffffff) % buckets.size();
    }

    /**
     * Inserts or updates the value for the given key.
     * @param key non-null key
     * @param value value to associate
     */
    public void put(K key, V value) {
        Objects.requireNonNull(key, "Key cannot be null");
        int idx = index(key);
        for (Entry<K, V> entry : buckets.get(idx)) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        buckets.get(idx).add(new Entry<>(key, value));
        size++;
    }

    /**
     * Returns the value associated with the key, or null if not found.
     */
    public V get(K key) {
        Objects.requireNonNull(key, "Key cannot be null");
        int idx = index(key);
        for (Entry<K, V> entry : buckets.get(idx)) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    /**
     * Removes the entry with the given key.
     * @return removed value or null if not found
     */
    public V remove(K key) {
        Objects.requireNonNull(key, "Key cannot be null");
        int idx = index(key);
        var bucket = buckets.get(idx);
        var it = bucket.iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            if (entry.key.equals(key)) {
                it.remove();
                size--;
                return entry.value;
            }
        }
        return null;
    }

    /**
     * Checks if the table contains the given key.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Returns the number of entries in the table.
     */
    public int size() {
        return size;
    }

    /**
     * Returns a list of all keys in the table.
     */
    public List<K> keys() {
        List<K> list = new ArrayList<>();
        for (var bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                list.add(entry.key);
            }
        }
        return list;
    }
} 