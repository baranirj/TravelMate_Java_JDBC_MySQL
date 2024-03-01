package com.travelmate.dao;

import com.travelmate.model.Destination;
import com.travelmate.model.Activity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DestinationDAOImpl implements DestinationDAO {

    private static final String SELECT_DESTINATIONS_BY_TRAVEL_PACKAGE_ID = "SELECT * FROM destinations WHERE travel_package_id=?";
    private static final String SELECT_DESTINATION_BY_ID = "SELECT * FROM destinations WHERE id=?";
    private static final String INSERT_DESTINATION = "INSERT INTO destinations(name, travel_package_id) VALUES(?, ?)";
    private static final String UPDATE_DESTINATION = "UPDATE destinations SET name=? WHERE id=?";
    private static final String DELETE_DESTINATION = "DELETE FROM destinations WHERE id=?";
    private static final String SELECT_ALL_DESTINATIONS = "SELECT * FROM destinations";

    private static final String INSERT_ACTIVITY = "INSERT INTO activities(name, description, cost, capacity, destination_id) VALUES(?, ?, ?, ?, ?)";


    @Override
    public List<Destination> getDestinationsByTravelPackageId(int travelPackageId) {
        List<Destination> destinations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_DESTINATIONS_BY_TRAVEL_PACKAGE_ID);
            preparedStatement.setInt(1, travelPackageId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Destination destination = extractDestinationFromResultSet(resultSet);
                destination.setActivities(getActivitiesByDestinationId(destination.getId()));
                destinations.add(destination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    @Override
    public Destination getDestinationById(int id) {
        Destination destination = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_DESTINATION_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                destination = extractDestinationFromResultSet(resultSet);
                destination.setActivities(getActivitiesByDestinationId(destination.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destination;
    }

    @Override
    public void addDestination(Destination destination) {
        Connection connection = null;
        PreparedStatement destinationStatement = null;
        PreparedStatement activityStatement = null;
        ResultSet generatedKeys = null;

        try {
            // Establish connection
            connection = DatabaseManager.getConnection();

            // Insert destination
            destinationStatement = connection.prepareStatement(INSERT_DESTINATION, Statement.RETURN_GENERATED_KEYS);
            destinationStatement.setString(1, destination.getName());
            destinationStatement.setInt(2, destination.getPackageId()); // Set packageId
            destinationStatement.executeUpdate();

            // Retrieve the generated destination ID
            generatedKeys = destinationStatement.getGeneratedKeys();
            int destinationId = -1;
            if (generatedKeys.next()) {
                destinationId = generatedKeys.getInt(1);
                destination.setId(destinationId); // Set the generated ID in the destination object
            } else {
                throw new SQLException("Failed to retrieve generated destination ID.");
            }

            // Insert activities
            List<Activity> activities = destination.getActivities();
            activityStatement = connection.prepareStatement(INSERT_ACTIVITY);
            for (Activity activity : activities) {
                activityStatement.setString(1, activity.getName());
                activityStatement.setString(2, activity.getDescription());
                activityStatement.setDouble(3, activity.getCost());
                activityStatement.setInt(4, activity.getCapacity());
                activityStatement.setInt(5, destinationId); // Set destination ID
                activityStatement.addBatch(); // Add batch for batch processing
            }
            // Execute batch
            activityStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void updateDestination(Destination destination) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_DESTINATION);
            preparedStatement.setString(1, destination.getName());
            preparedStatement.setInt(2, destination.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDestination(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_DESTINATION);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Destination> getAllDestination() {
        List<Destination> destinations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_DESTINATIONS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Destination destination = extractDestinationFromResultSet(resultSet);
                destinations.add(destination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    private Destination extractDestinationFromResultSet(ResultSet resultSet) throws SQLException {
        Destination destination = new Destination();
        destination.setId(resultSet.getInt("id"));
        destination.setName(resultSet.getString("name"));
        // Extract other destination attributes if needed
        return destination;
    }

    private List<Activity> getActivitiesByDestinationId(int destinationId) {
        List<Activity> activities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            // Replace the query with the appropriate query to fetch activities based on destination ID
            String query = "SELECT * FROM activities WHERE destination_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, destinationId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Activity activity = new Activity();
                activity.setId(resultSet.getInt("id"));
                activity.setName(resultSet.getString("name"));
                // Populate other activity attributes if needed
                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }
}
