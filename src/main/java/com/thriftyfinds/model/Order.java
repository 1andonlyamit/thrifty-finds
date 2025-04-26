package com.thriftyfinds.model;

import java.util.Date;

/**
 * Represents an order in the Thrifty Finds application
 */
public class Order {
    private int id;
    private int productId;
    private int buyerId;
    private String buyerName;
    private String shippingAddress;
    private String contactPhone;
    private String paymentMethod;
    private double totalAmount;
    private Date orderDate;
    private String status; // "pending", "completed", "cancelled"

    // Store product details for display purposes
    private String productTitle;
    private String productImage;
    private double productPrice;
    private int sellerId;
    private String sellerUsername;

    // Default constructor
    public Order() {
        this.orderDate = new Date();
        this.status = "pending";
    }

    // Constructor with essential fields
    public Order(int productId, int buyerId, String shippingAddress,
            String contactPhone, String paymentMethod, double totalAmount) {
        this.productId = productId;
        this.buyerId = buyerId;
        this.shippingAddress = shippingAddress;
        this.contactPhone = contactPhone;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.orderDate = new Date();
        this.status = "pending";
    }

    // Constructor with all fields
    public Order(int id, int productId, int buyerId, String buyerName,
            String shippingAddress, String contactPhone, String paymentMethod,
            double totalAmount, Date orderDate, String status) {
        this.id = id;
        this.productId = productId;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.shippingAddress = shippingAddress;
        this.contactPhone = contactPhone;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId=" + productId +
                ", buyerId=" + buyerId +
                ", buyerName='" + buyerName + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmount=" + totalAmount +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productPrice=" + productPrice +
                ", sellerId=" + sellerId +
                ", sellerUsername='" + sellerUsername + '\'' +
                '}';
    }
}