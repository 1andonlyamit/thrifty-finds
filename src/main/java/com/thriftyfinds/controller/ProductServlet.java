package com.thriftyfinds.controller;

import com.thriftyfinds.dao.ProductDAO;
import com.thriftyfinds.model.Product;
import com.thriftyfinds.util.ImageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

/**
 * Servlet handling product management
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/add-product", "/product"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,      // 1 MB
    maxFileSize = 1024 * 1024 * 10,       // 10 MB
    maxRequestSize = 1024 * 1024 * 100    // 100 MB
)
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        if ("/add-product".equals(path)) {
            // Check if user is logged in
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            
            request.getRequestDispatcher("/add-product.jsp").forward(request, response);
        } else if ("/product".equals(path)) {
            // Display product details
            String productIdStr = request.getParameter("id");
            
            if (productIdStr == null || productIdStr.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            
            try {
                int productId = Integer.parseInt(productIdStr);
                Product product = productDAO.getProductById(productId);
                
                if (product != null) {
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("/product-detail.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        if ("/add-product".equals(path)) {
            // Check if user is logged in
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            
            // Handle product addition
            handleAddProduct(request, response);
        }
    }
    
    /**
     * Handles product addition
     */
    private void handleAddProduct(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get user ID from session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        // Get form data
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        Part imagePart = request.getPart("image");
        
        // Validate input data
        boolean hasError = false;
        
        if (title == null || title.trim().isEmpty()) {
            request.setAttribute("titleError", "Title is required");
            hasError = true;
        }
        
        if (priceStr == null || priceStr.trim().isEmpty()) {
            request.setAttribute("priceError", "Price is required");
            hasError = true;
        }
        
        double price = 0.0;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                request.setAttribute("priceError", "Price must be greater than 0");
                hasError = true;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("priceError", "Invalid price");
            hasError = true;
        }
        
        if (imagePart == null || imagePart.getSize() <= 0 || imagePart.getSubmittedFileName().isEmpty()) {
            request.setAttribute("imageError", "Image is required");
            hasError = true;
        } else {
            String fileName = imagePart.getSubmittedFileName();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            
            if (!fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png")) {
                request.setAttribute("imageError", "Only JPG and PNG images are allowed");
                hasError = true;
            }
        }
        
        if (hasError) {
            // Keep the input values
            request.setAttribute("title", title);
            request.setAttribute("description", description);
            request.setAttribute("price", priceStr);
            
            request.getRequestDispatcher("/add-product.jsp").forward(request, response);
            return;
        }
        
        // Process image upload
        String imageFileName = null;
        
        if (imagePart != null && imagePart.getSize() > 0) {
            // Generate unique filename
            String fileName = imagePart.getSubmittedFileName();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
            imageFileName = UUID.randomUUID().toString() + "." + fileExt;
            
            // Save image to upload directory
            String uploadPath = getServletContext().getRealPath("/uploads");
            ImageUtil.saveImage(imagePart, uploadPath, imageFileName);
        }
        
        // Create and save the product
        Product product = new Product(title, description, price, imageFileName, userId);
        int productId = productDAO.addProduct(product);
        
        if (productId > 0) {
            // Product added successfully
            session.setAttribute("message", "Product added successfully!");
            response.sendRedirect(request.getContextPath() + "/product?id=" + productId);
        } else {
            // Failed to add product
            request.setAttribute("errorMessage", "Failed to add product. Please try again.");
            
            // Keep the input values
            request.setAttribute("title", title);
            request.setAttribute("description", description);
            request.setAttribute("price", priceStr);
            
            request.getRequestDispatcher("/add-product.jsp").forward(request, response);
        }
    }
}
