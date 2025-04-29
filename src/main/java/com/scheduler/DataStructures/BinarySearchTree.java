package main.java.com.scheduler.DataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple generic binary search tree implementation.
 * @param <K> key type, must be Comparable
 * @param <V> value type
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
    private Node<K, V> root;
    private int size;

    /**
     * Node class representing each tree element.
     */
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Inserts or updates a key-value mapping in the BST.
     * @param key non-null key
     * @param value value to associate
     */
    public void put(K key, V value) {
        Objects.requireNonNull(key, "Key cannot be null");
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    /**
     * Retrieves the value associated with the given key, or null if not found.
     */
    public V get(K key) {
        Objects.requireNonNull(key, "Key cannot be null");
        Node<K, V> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }

    /**
     * Removes the mapping for the given key.
     * @param key non-null key
     * @return removed value or null if not found
     */
    public V remove(K key) {
        Objects.requireNonNull(key, "Key cannot be null");
        V[] removed = (V[]) new Object[1];
        root = remove(root, key, removed);
        if (removed[0] != null) size--;
        return removed[0];
    }

    @SuppressWarnings("unchecked")
    private Node<K, V> remove(Node<K, V> node, K key, V[] removed) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key, removed);
        } else if (cmp > 0) {
            node.right = remove(node.right, key, removed);
        } else {
            // capture removed value only if not already set (prevents successor removal overwrite)
            if (removed[0] == null) {
                removed[0] = node.value;
            }
            // remove this node
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // two children: find successor
            Node<K, V> successor = min(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = remove(node.right, successor.key, removed);
        }
        return node;
    }

    private Node<K, V> min(Node<K, V> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /**
     * Checks if the BST contains the key.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Returns the number of elements in the BST.
     */
    public int size() {
        return size;
    }

    /**
     * Returns values in in-order traversal (sorted by key).
     */
    public List<V> values() {
        List<V> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    private void inOrder(Node<K, V> node, List<V> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(node.value);
        inOrder(node.right, list);
    }
} 