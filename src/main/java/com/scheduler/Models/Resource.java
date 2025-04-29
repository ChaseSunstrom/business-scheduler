package main.java.com.scheduler.Models;

import java.util.Objects;

/**
 * Represents a resource with an id, name, and type.
 */
public class Resource {
    private final String id;
    private String name;
    private String type;

    /**
     * Constructs a Resource with the given id, name, and type.
     * @param id unique identifier, non-null and non-empty
     * @param name resource name, non-null and non-empty
     * @param type resource type, non-null and non-empty
     */
    public Resource(String id, String name, String type) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Resource id cannot be null or empty");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Resource name cannot be null or empty");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Resource type cannot be null or empty");
        }
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    /**
     * Updates the name of this resource.
     * @param name new name, non-null and non-empty
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Resource name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Updates the type of this resource.
     * @param type new type, non-null and non-empty
     */
    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Resource type cannot be null or empty");
        }
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resource)) return false;
        Resource that = (Resource) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, type);
    }
} 