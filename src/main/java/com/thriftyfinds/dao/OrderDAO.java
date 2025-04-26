package com.thriftyfinds.dao;

import com.thriftyfinds.model.Order;
import com.thriftyfinds.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Order-related database operations
 */
public class OrderDAO {
    // SQL statements
    private static final String CREATE_ORDERS_TABLE = "CREATE TABLE IF NOT EXISTS orders (" +
            "id SERIAL PRIMARY KEY," +
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

    private static final String INSERT_ORDER = "INSERT INTO orders (product_id, buyer_id, shipping_address, contact_phone, "
            +
            "payment_method, total_amount) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PRODUCT_AVAILABILITY = "UPDATE products SET is_available = false WHERE id = ?";

    private static final String GET_ORDER_BY_ID = "SELECT o.*, u.username as buyer_name, " +
            "p.title as product_title, p.image_filename as product_image, p.price as product_price, " +
            "p.seller_id, u2.username as seller_username " +
            "FROM orders o " +
            "JOIN users u ON o.buyer_id = u.id " +
            "JOIN products p ON o.product_id = p.id " +
            "JOIN users u2 ON p.seller_id = u2.id " +
            "WHERE o.id = ?";

    private static final String GET_ORDERS_BY_BUYER = "SELECT o.*, u.username as buyer_name, " +
            "p.title as product_title, p.image_filename as product_image, p.price as product_price, " +
            "p.seller_id, u2.username as seller_username " +
            "FROM orders o " +
            "JOIN users u ON o.buyer_id = u.id " +
            "JOIN products p ON o.product_id = p.id " +
            "JOIN users u2 ON p.seller_id = u2.id " +
            "WHERE o.buyer_id = ? " +
            "ORDER BY o.order_date DESC";

    private static final String UPDATE_ORDER_STATUS = "UPDATE orders SET status = ? WHERE id = ?";

    /**
     * Initialize the orders table if it doesn't exist
     */
    public static void initializeTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.createStatement();

            // Check if table exists by querying the information schema
            ResultSet rs = statement.executeQuery(
                    "SELECT table_name FROM information_schema.tables " +
                            "WHERE table_schema = 'public' AND table_name = 'orders'");

            boolean ordersTableExists = rs.next();

            // Drop existing table if it exists
            if (ordersTableExists) {
                statement.executeUpdate("DROP TABLE orders");
                System.out.println("Orders table dropped");
            }

            // Create orders table
            statement.executeUpdate(CREATE_ORDERS_TABLE);
            System.out.println("Orders table created successfully");

        } catch (SQLException e) {
            System.err.println("Error initializing orders table");
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

    /**
     * Creates a new order and marks the product as unavailable
     * 
     * @param order Order to create
     * @return newly created order with ID
     */
    public Order createOrder(Order order) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Insert order
            pstmt = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, order.getProductId());
            pstmt.setInt(2, order.getBuyerId());
            pstmt.setString(3, order.getShippingAddress());
            pstmt.setString(4, order.getContactPhone());
            pstmt.setString(5, order.getPaymentMethod());
            pstmt.setDouble(6, order.getTotalAmount());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }

            // Close the first prepared statement
            pstmt.close();

            // Update product availability
            pstmt = connection.prepareStatement(UPDATE_PRODUCT_AVAILABILITY);
            pstmt.setInt(1, order.getProductId());
            pstmt.executeUpdate();

            connection.commit(); // Commit transaction

            // Get the full order details
            return getOrderById(order.getId());

        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback transaction on error
                }
            } catch (SQLException ex) {
                System.err.println("Error rolling back transaction");
                ex.printStackTrace();
            }
            System.err.println("Error creating order");
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true); // Reset auto-commit
                    DBConnection.getInstance().closeConnection(connection);
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources");
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets an order by its ID with product and user details
     * 
     * @param orderId ID of the order to retrieve
     * @return Order object with all details or null if not found
     */
    public Order getOrderById(int orderId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Order order = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement(GET_ORDER_BY_ID);
            pstmt.setInt(1, orderId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setProductId(rs.getInt("product_id"));
                order.setBuyerId(rs.getInt("buyer_id"));
                order.setBuyerName(rs.getString("buyer_name"));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setContactPhone(rs.getString("contact_phone"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setStatus(rs.getString("status"));

                // Product details
                order.setProductTitle(rs.getString("product_title"));
                order.setProductImage(rs.getString("product_image"));
                order.setProductPrice(rs.getDouble("product_price"));
                order.setSellerId(rs.getInt("seller_id"));
                order.setSellerUsername(rs.getString("seller_username"));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving order");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    DBConnection.getInstance().closeConnection(connection);
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources");
                e.printStackTrace();
            }
        }

        return order;
    }

    /**
     * Gets all orders for a specific buyer
     * 
     * @param buyerId ID of the buyer
     * @return List of orders with product and user details
     */
    public List<Order> getOrdersByBuyer(int buyerId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement(GET_ORDERS_BY_BUYER);
            pstmt.setInt(1, buyerId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setProductId(rs.getInt("product_id"));
                order.setBuyerId(rs.getInt("buyer_id"));
                order.setBuyerName(rs.getString("buyer_name"));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setContactPhone(rs.getString("contact_phone"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setStatus(rs.getString("status"));

                // Product details
                order.setProductTitle(rs.getString("product_title"));
                order.setProductImage(rs.getString("product_image"));
                order.setProductPrice(rs.getDouble("product_price"));
                order.setSellerId(rs.getInt("seller_id"));
                order.setSellerUsername(rs.getString("seller_username"));

                orders.add(order);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving orders");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    DBConnection.getInstance().closeConnection(connection);
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources");
                e.printStackTrace();
            }
        }

        return orders;
    }

    /**
     * Updates the status of an order
     * 
     * @param orderId ID of the order
     * @param status  New status value
     * @return true if update was successful
     */
    public boolean updateOrderStatus(int orderId, String status) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement(UPDATE_ORDER_STATUS);
            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error updating order status");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
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