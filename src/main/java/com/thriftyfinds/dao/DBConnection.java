package com.thriftyfinds.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for database connection management
 */
public class DBConnection {
    // MySQL database connection parameters
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "thriftyfinds";
    private static final String USER = "root";
    private static final String PASSWORD = "7073";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + 
                                     "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    
    private static DBConnection instance;
    
    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found");
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the singleton instance of DBConnection
     * @return DBConnection instance
     */
    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
    
    /**
     * Creates and returns a connection to the database
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    /**
     * Closes a connection safely
     * @param connection Connection to close
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection");
                e.printStackTrace();
            }
        }
    }
}
