package com.thriftyfinds.controller;

import com.thriftyfinds.dao.ProductDAO;
import com.thriftyfinds.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet handling the home page
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home", ""})
public class HomeServlet extends HttpServlet {
    private ProductDAO productDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Fetch all products
        List<Product> products = productDAO.getAllProducts();
        
        // Set attributes and forward to home page
        request.setAttribute("products", products);
        
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
