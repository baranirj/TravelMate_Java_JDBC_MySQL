package com.travelmate.model;

public class Activity {
    private int id; // Add an id field to uniquely identify activities
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private int destinationId;

    public Activity() {
    }

    public Activity(String name, String description, double cost, int capacity, int destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destinationId = destination;
    }

    public Activity(String activityName, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
    }

    // Add getters and setters for the id field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Activity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Activity setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Activity setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public Activity setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public Activity setDestinationId(int destinationId) {
        this.destinationId = destinationId;
        return this;
    }

    // Other getters and setters remain the same
    // ...


    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", capacity=" + capacity +
                ", destinationId=" + destinationId +
                '}';
    }
}
