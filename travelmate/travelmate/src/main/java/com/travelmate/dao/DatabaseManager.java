package com.travelmate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static Connection connection;

    // Method to establish connection to MySQL database
    public static void establishConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");

            // Create the database if it doesn't exist
            createDatabaseIfNotExists();

            // Connect to the "travelagency" database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelagency", "root", "root");

            System.out.println("Connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found");
            e.printStackTrace();
            throw new SQLException("MySQL JDBC driver not found", e);
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            throw e;
        }
    }

    // Method to create the database if it doesn't exist
    private static void createDatabaseIfNotExists() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS travelagency");
            System.out.println("Database 'travelagency' created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating database 'travelagency'");
            e.printStackTrace();
            throw e;
        }
    }

    // Method to close connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("Error closing connection.");
                e.printStackTrace();
            }
        }
    }

    // Getter method for accessing the connection
    public static Connection getConnection() {
        return connection;
    }
}
