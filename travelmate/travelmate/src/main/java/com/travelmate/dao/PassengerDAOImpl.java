package com.travelmate.dao;

import com.travelmate.model.Passenger;
import com.travelmate.model.PassengerType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAOImpl implements PassengerDAO {

    private static final String SELECT_PASSENGERS_BY_TRAVEL_PACKAGE_ID = "SELECT * FROM passengers WHERE travel_package_id=?";
    private static final String SELECT_PASSENGER_BY_ID = "SELECT * FROM passengers WHERE id=?";
    private static final String INSERT_PASSENGER = "INSERT INTO passengers(name, passenger_number, passenger_type, balance, travel_package_id) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_PASSENGER = "UPDATE passengers SET name=?, passenger_number=?, passenger_type=?, balance=? WHERE id=?";
    private static final String DELETE_PASSENGER = "DELETE FROM passengers WHERE id=?";
    private static final String SELECT_ALL_PASSENGERS = "SELECT * FROM passengers";

    @Override
    public List<Passenger> getPassengersByTravelPackageId(int travelPackageId) {
        List<Passenger> passengers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_PASSENGERS_BY_TRAVEL_PACKAGE_ID);
            preparedStatement.setInt(1, travelPackageId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Passenger passenger = extractPassengerFromResultSet(resultSet);
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }


    @Override
    public List<Passenger> getAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_PASSENGERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Passenger passenger = extractPassengerFromResultSet(resultSet);
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    @Override
    public Passenger getPassengerById(int id) {
        Passenger passenger = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_PASSENGER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                passenger = extractPassengerFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public void addPassenger(Passenger passenger) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PASSENGER);
            setPassengerParameters(preparedStatement, passenger);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassenger(Passenger passenger) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PASSENGER);
            setPassengerParameters(preparedStatement, passenger);
            preparedStatement.setInt(5, passenger.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePassenger(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_PASSENGER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Passenger extractPassengerFromResultSet(ResultSet resultSet) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setId(resultSet.getInt("id"));
        passenger.setName(resultSet.getString("name"));
        passenger.setPassengerNumber(resultSet.getInt("passenger_number"));
        passenger.setPassengerType(PassengerType.valueOf(resultSet.getString("passenger_type")));
        passenger.setBalance(resultSet.getDouble("balance"));
        // Set other passenger attributes if needed
        return passenger;
    }

    private void setPassengerParameters(PreparedStatement preparedStatement, Passenger passenger) throws SQLException {
        preparedStatement.setString(1, passenger.getName());
        preparedStatement.setInt(2, passenger.getPassengerNumber());
        preparedStatement.setString(3, passenger.getPassengerType().name());
        preparedStatement.setDouble(4, passenger.getBalance());
        preparedStatement.setInt(5, passenger.getTravelPackageId());
    }
}
