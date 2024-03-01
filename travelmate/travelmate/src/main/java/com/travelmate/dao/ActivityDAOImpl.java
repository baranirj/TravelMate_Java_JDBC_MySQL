package com.travelmate.dao;

import com.travelmate.model.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAOImpl implements ActivityDAO {

    private static final String SELECT_AVAILABLE_ACTIVITIES = "SELECT * FROM activities WHERE destination_id=?";
    private static final String SELECT_ACTIVITY_BY_ID = "SELECT * FROM activities WHERE id=?";
    private static final String INSERT_ACTIVITY = "INSERT INTO activities(name, description, cost, capacity, destination_id) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_ACTIVITY = "UPDATE activities SET name=?, description=?, cost=?, capacity=?, destination_id=? WHERE id=?";
    private static final String DELETE_ACTIVITY = "DELETE FROM activities WHERE id=?";
    private static final String SELECT_ALL_ACTIVITIES = "SELECT * FROM activities";

    @Override
    public List<Activity> getAvailableActivities(int destinationId) {
        List<Activity> activities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_AVAILABLE_ACTIVITIES);
            preparedStatement.setInt(1, destinationId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Activity activity = extractActivityFromResultSet(resultSet);
                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    @Override
    public List<Activity> getActivitiesByDestinationId(int destinationId) {
        List<Activity> activities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_AVAILABLE_ACTIVITIES);
            preparedStatement.setInt(1, destinationId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Activity activity = extractActivityFromResultSet(resultSet);
                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    @Override
    public Activity getActivityById(int id) {
        Activity activity = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ACTIVITY_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                activity = extractActivityFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }

    @Override
    public void addActivity(Activity activity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ACTIVITY);
            setActivityParameters(preparedStatement, activity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateActivity(Activity activity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ACTIVITY);
            setActivityParameters(preparedStatement, activity);
            preparedStatement.setInt(6, activity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteActivity(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ACTIVITY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Activity> getAllActivity() {
        List<Activity> activities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_ACTIVITIES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Activity activity = extractActivityFromResultSet(resultSet);
                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    private Activity extractActivityFromResultSet(ResultSet resultSet) throws SQLException {
        Activity activity = new Activity();
        activity.setId(resultSet.getInt("id"));
        activity.setName(resultSet.getString("name"));
        activity.setDescription(resultSet.getString("description"));
        activity.setCost(resultSet.getDouble("cost"));
        activity.setCapacity(resultSet.getInt("capacity"));
        // Set destination if needed
        // activity.setDestination(destination);
        return activity;
    }

    private void setActivityParameters(PreparedStatement preparedStatement, Activity activity) throws SQLException {
        preparedStatement.setString(1, activity.getName());
        preparedStatement.setString(2, activity.getDescription());
        preparedStatement.setDouble(3, activity.getCost());
        preparedStatement.setInt(4, activity.getCapacity());
        preparedStatement.setInt(5, activity.getDestinationId());
    }
}
