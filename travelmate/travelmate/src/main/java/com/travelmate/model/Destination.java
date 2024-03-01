package com.travelmate.model;

import java.util.List;

public class Destination {

    private int id;
    private String name;

    private int packageId;
    private List<Activity> activities;

    // Constructor


    public Destination() {
    }

    public int getPackageId() {
        return packageId;
    }

    public Destination setPackageId(int packageId) {
        this.packageId = packageId;
        return this;
    }

    public Destination(String name, List<Activity> activities) {
        this.name = name;
        this.activities = activities;
    }

    public Destination(String name) {
    }

    // Getters and setters


    public int getId() {
        return id;
    }

    public Destination setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}

