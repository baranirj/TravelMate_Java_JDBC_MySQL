package com.travelmate.service;

import com.travelmate.dao.ActivityDAO;
import com.travelmate.model.Activity;

import java.util.List;

public class ActivityServiceImpl implements ActivityService {

    private final ActivityDAO activityDAO;

    public ActivityServiceImpl(ActivityDAO activityDAO) {
        this.activityDAO = activityDAO;
    }

    @Override
    public List<Activity> getAvailableActivities(int destinationId) {
        return activityDAO.getAvailableActivities(destinationId);
    }

    @Override
    public List<Activity> getActivitiesByDestinationId(int destinationId) {
        return activityDAO.getActivitiesByDestinationId(destinationId);
    }

    @Override
    public Activity getActivityById(int id) {
        return activityDAO.getActivityById(id);
    }

    @Override
    public void addActivity(Activity activity) {
        activityDAO.addActivity(activity);
    }

    @Override
    public void updateActivity(Activity activity) {
        activityDAO.updateActivity(activity);
    }

    @Override
    public void deleteActivity(int id) {
        activityDAO.deleteActivity(id);
    }

    @Override
    public List<Activity> getAllActivities() {


        return activityDAO.getAllActivity();
    }
}
