package com.travelmate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitializer {



    // Method to create the travel_packages table
    public static void createTravelPackageTable() {
        // SQL statement to create the travel_packages table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS travel_packages (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "passenger_capacity INT NOT NULL" +
                ")";

        // Execute the SQL statement
        executeUpdate(sql);

        // Log message to indicate table creation or existence
        System.out.println("Table 'travel_packages' created or already exists.");
    }

    // Method to create the destinations table
    public static void createDestinationTable() {
        // SQL statement to create the destinations table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS destinations (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "travel_package_id INT NOT NULL," +
                "FOREIGN KEY (travel_package_id) REFERENCES travel_packages(id)" +
                ")";
        // Execute the SQL statement
        executeUpdate(sql);

        // indicate table creation or existence
        System.out.println("Table 'destinations' created or already exists.");
    }

    // Method to create the activities table
    public static void createActivityTable() {
        // SQL statement to create the activities table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS activities (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "description VARCHAR(255)," +
                "cost DECIMAL(10,2) NOT NULL," +
                "capacity INT NOT NULL," +
                "destination_id INT NOT NULL," +
                "FOREIGN KEY (destination_id) REFERENCES destinations(id)" +
                ")";

        // Execute the SQL statement
        executeUpdate(sql);

        // Log message to indicate table creation or existence
        System.out.println("Table 'activities' created or already exists.");
    }

    // Method to create the passengers table
    public static void createPassengerTable() {
        // SQL statement to create the passengers table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS passengers (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "passenger_number INT NOT NULL," +
                "passenger_type VARCHAR(50) NOT NULL," +
                "travel_package_id INT NOT NULL," +
                "balance INT NOT NULL," +

                "FOREIGN KEY (travel_package_id) REFERENCES travel_packages(id)" +
                ")";

        // Execute the SQL statement
        executeUpdate(sql);

        System.out.println("Table 'passengers' created or already exists.");
    }

    // Method to execute SQL update statements
    private static void executeUpdate(String sql) {
        try {
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            // Execute the SQL update statement
            statement.executeUpdate();
            // Close statement
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
