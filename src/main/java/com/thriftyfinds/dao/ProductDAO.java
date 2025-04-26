package com.thriftyfinds.dao;

import com.thriftyfinds.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Product-related database operations
 */
public class ProductDAO {
    
    /**
     * Adds a new product
     * @param product Product object containing product data
     * @return Product ID if successful, -1 otherwise
     */
    public int addProduct(Product product) {
        String sql = "INSERT INTO products (title, description, price, image_filename, seller_id) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, product.getTitle());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getImageFilename());
            statement.setInt(5, product.getSellerId());
            
            int rowsInserted = statement.executeUpdate();
            
            if (rowsInserted > 0) {
                generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding product");
            e.printStackTrace();
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
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
        
        return -1;
    }
    
    /**
     * Gets all products
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        String sql = "SELECT p.*, u.username AS seller_username FROM products p " +
                     "JOIN users u ON p.seller_id = u.id " +
                     "WHERE p.is_available = true " +
                     "ORDER BY p.created_at DESC";
        
        return getProductsWithQuery(sql);
    }
    
    /**
     * Gets a product by ID
     * @param productId Product ID
     * @return Product object if found, null otherwise
     */
    public Product getProductById(int productId) {
        String sql = "SELECT p.*, u.username AS seller_username FROM products p " +
                     "JOIN users u ON p.seller_id = u.id " +
                     "WHERE p.id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return extractProductFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Error getting product by ID");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
        
        return null;
    }
    
    /**
     * Gets products by seller ID
     * @param sellerId Seller ID
     * @return List of products by the seller
     */
    public List<Product> getProductsBySellerId(int sellerId) {
        String sql = "SELECT p.*, u.username AS seller_username FROM products p " +
                     "JOIN users u ON p.seller_id = u.id " +
                     "WHERE p.seller_id = ? " +
                     "ORDER BY p.created_at DESC";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();
        
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, sellerId);
            
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Error getting products by seller ID");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
        
        return products;
    }
    
    /**
     * Searches for products by keyword in title or description
     * @param keyword Search keyword
     * @return List of matching products
     */
    public List<Product> searchProducts(String keyword) {
        String sql = "SELECT p.*, u.username AS seller_username FROM products p " +
                     "JOIN users u ON p.seller_id = u.id " +
                     "WHERE (p.title LIKE ? OR p.description LIKE ?) AND p.is_available = true " +
                     "ORDER BY p.created_at DESC";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();
        
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            String searchPattern = "%" + keyword + "%";
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);
            
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Error searching products");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
        
        return products;
    }
    
    /**
     * Helper method to extract a Product object from a ResultSet
     * @param resultSet ResultSet containing product data
     * @return Product object
     * @throws SQLException if ResultSet processing fails
     */
    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setTitle(resultSet.getString("title"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getDouble("price"));
        product.setImageFilename(resultSet.getString("image_filename"));
        product.setSellerId(resultSet.getInt("seller_id"));
        product.setSellerUsername(resultSet.getString("seller_username"));
        product.setCreatedAt(resultSet.getTimestamp("created_at"));
        product.setAvailable(resultSet.getBoolean("is_available"));
        
        return product;
    }
    
    /**
     * Helper method to get products using a specified SQL query
     * @param sql SQL query
     * @return List of products
     */
    private List<Product> getProductsWithQuery(String sql) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();
        
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + sql);
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
        
        return products;
    }
}
