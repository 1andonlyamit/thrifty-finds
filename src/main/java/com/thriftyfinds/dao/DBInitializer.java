package com.thriftyfinds.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Initializes the database tables if they don't exist
 */
public class DBInitializer {
    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "username VARCHAR(50) NOT NULL UNIQUE," +
            "email VARCHAR(100) NOT NULL UNIQUE," +
            "password VARCHAR(100) NOT NULL," +
            "full_name VARCHAR(100) NOT NULL," +
            "registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")";

    private static final String CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS products (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "title VARCHAR(100) NOT NULL," +
            "description TEXT," +
            "price DECIMAL(10,2) NOT NULL," +
            "image_filename VARCHAR(255)," +
            "seller_id INT NOT NULL," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "is_available BOOLEAN DEFAULT TRUE," +
            "FOREIGN KEY (seller_id) REFERENCES users(id)" +
            ")";

    private static final String CREATE_ORDERS_TABLE = "CREATE TABLE IF NOT EXISTS orders (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "product_id INT NOT NULL," +
            "buyer_id INT NOT NULL," +
            "shipping_address TEXT NOT NULL," +
            "contact_phone VARCHAR(20) NOT NULL," +
            "payment_method VARCHAR(50) NOT NULL," +
            "total_amount DECIMAL(10,2) NOT NULL," +
            "order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "status VARCHAR(20) DEFAULT 'pending'," +
            "FOREIGN KEY (product_id) REFERENCES products(id)," +
            "FOREIGN KEY (buyer_id) REFERENCES users(id)" +
            ")";

    /**
     * Initializes the database by creating necessary tables if they don't exist
     */
    public static void initialize() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.createStatement();
            // Create tables in correct order
            statement.executeUpdate(CREATE_USERS_TABLE);
            System.out.println("Users table created successfully");

            statement.executeUpdate(CREATE_PRODUCTS_TABLE);
            System.out.println("Products table created successfully");

            statement.executeUpdate(CREATE_ORDERS_TABLE);
            System.out.println("Orders table created successfully");

        } catch (SQLException e) {
            System.err.println("Error initializing database");
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    DBConnection.getInstance().closeConnection(connection);
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources");
                e.printStackTrace();
            }
        }
    }
}
