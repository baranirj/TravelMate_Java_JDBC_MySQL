package com.travelmate.dao;


import com.travelmate.model.Activity;

import java.util.List;

public interface ActivityDAO {

    public List<Activity> getAvailableActivities(int destinationId);
    List<Activity> getActivitiesByDestinationId(int destinationId);
    Activity getActivityById(int id);
    void addActivity(Activity activity);
    void updateActivity(Activity activity);
    void deleteActivity(int id);

    List<Activity> getAllActivity();
}
