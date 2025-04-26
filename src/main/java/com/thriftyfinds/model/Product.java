package com.thriftyfinds.model;

import java.util.Date;

/**
 * Represents a product in the Thrifty Finds application
 */
public class Product {
    private int id;
    private String title;
    private String description;
    private double price;
    private String imageFilename;
    private int sellerId;
    private String sellerUsername;
    private Date createdAt;
    private boolean isAvailable;

    // Default constructor
    public Product() {
    }

    // Constructor with essential fields
    public Product(String title, String description, double price, String imageFilename, int sellerId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageFilename = imageFilename;
        this.sellerId = sellerId;
        this.createdAt = new Date();
        this.isAvailable = true;
    }

    // Constructor with all fields
    public Product(int id, String title, String description, double price,
            String imageFilename, int sellerId, String sellerUsername,
            Date createdAt, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageFilename = imageFilename;
        this.sellerId = sellerId;
        this.sellerUsername = sellerUsername;
        this.createdAt = createdAt;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageFilename='" + imageFilename + '\'' +
                ", sellerId=" + sellerId +
                ", sellerUsername='" + sellerUsername + '\'' +
                ", createdAt=" + createdAt +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
