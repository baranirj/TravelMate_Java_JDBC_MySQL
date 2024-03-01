package com.travelmate.dao;

import com.travelmate.model.Destination;
import com.travelmate.model.TravelPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TravelPackageDAOImpl implements TravelPackageDAO {

    private static final String SELECT_ALL_TRAVEL_PACKAGES = "SELECT * FROM travel_packages";
    private static final String SELECT_TRAVEL_PACKAGE_BY_ID = "SELECT * FROM travel_packages WHERE id=?";
    private static final String INSERT_TRAVEL_PACKAGE = "INSERT INTO travel_packages(name, passenger_capacity) VALUES(?, ?)";
    private static final String UPDATE_TRAVEL_PACKAGE = "UPDATE travel_packages SET name=?, passenger_capacity=? WHERE id=?";
    private static final String DELETE_TRAVEL_PACKAGE = "DELETE FROM travel_packages WHERE id=?";

    @Override
    public List<TravelPackage> getAllTravelPackages() {
        List<TravelPackage> travelPackages = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_TRAVEL_PACKAGES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TravelPackage travelPackage = new TravelPackage();
                travelPackage.setId(resultSet.getInt("id"));
                travelPackage.setName(resultSet.getString("name"));
                travelPackage.setPassengerCapacity(resultSet.getInt("passenger_capacity"));
                // Fetch destinations for the travel package
                List<Destination> destinations = getDestinationsByTravelPackageId(travelPackage.getId(), connection);
                travelPackage.setDestinations(destinations);
                travelPackages.add(travelPackage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travelPackages;
    }

    private List<Destination> getDestinationsByTravelPackageId(int travelPackageId, Connection connection) {
        List<Destination> destinations = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM destinations WHERE travel_package_id=?");
            preparedStatement.setInt(1, travelPackageId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Destination destination = new Destination();
                destination.setId(resultSet.getInt("id"));
                destination.setName(resultSet.getString("name"));
                // Populate other destination fields if needed
                destinations.add(destination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    @Override
    public TravelPackage getTravelPackageById(int id) {
        TravelPackage travelPackage = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_TRAVEL_PACKAGE_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                travelPackage = new TravelPackage();
                travelPackage.setId(resultSet.getInt("id"));
                travelPackage.setName(resultSet.getString("name"));
                travelPackage.setPassengerCapacity(resultSet.getInt("passenger_capacity"));
                // Fetch destinations for the travel package
                List<Destination> destinations = getDestinationsByTravelPackageId(travelPackage.getId(), connection);
                travelPackage.setDestinations(destinations);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travelPackage;
    }

    @Override
    public void addTravelPackage(TravelPackage travelPackage) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_TRAVEL_PACKAGE);
            preparedStatement.setString(1, travelPackage.getName());
            preparedStatement.setInt(2, travelPackage.getPassengerCapacity());
            preparedStatement.executeUpdate();
            // Add destinations for the travel package
//            addDestinations(travelPackage, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTravelPackage(TravelPackage travelPackage) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_TRAVEL_PACKAGE);
            preparedStatement.setString(1, travelPackage.getName());
            preparedStatement.setInt(2, travelPackage.getPassengerCapacity());
            preparedStatement.setInt(3, travelPackage.getId());
            preparedStatement.executeUpdate();
            // Update destinations for the travel package
            updateDestinations(travelPackage, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTravelPackage(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_TRAVEL_PACKAGE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            // Delete destinations for the travel package
            deleteDestinationsByTravelPackageId(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper methods for handling destinations
//    private void addDestinations(TravelPackage travelPackage, Connection connection) {
//        List<Destination> destinations = travelPackage.getDestinations();
//        PreparedStatement preparedStatement = null;
//        try {
//            for (Destination destination : destinations) {
//                preparedStatement = connection.prepareStatement("INSERT INTO destinations(name, travel_package_id) VALUES(?, ?)");
//                preparedStatement.setString(1, destination.getName());
//                preparedStatement.setInt(2, travelPackage.getId());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private void updateDestinations(TravelPackage travelPackage, Connection connection) {
        List<Destination> destinations = travelPackage.getDestinations();
        PreparedStatement preparedStatement = null;
        try {
            for (Destination destination : destinations) {
                preparedStatement = connection.prepareStatement("UPDATE destinations SET name=? WHERE id=?");
                preparedStatement.setString(1, destination.getName());
                preparedStatement.setInt(2, destination.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteDestinationsByTravelPackageId(int travelPackageId, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM destinations WHERE travel_package_id=?");
            preparedStatement.setInt(1, travelPackageId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
