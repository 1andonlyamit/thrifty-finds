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
 * Servlet handling product search functionality
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    private ProductDAO productDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String query = request.getParameter("q");
        
        if (query == null || query.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        
        // Search for products
        List<Product> searchResults = productDAO.searchProducts(query);
        
        // Set attributes and forward to search results page
        request.setAttribute("searchQuery", query);
        request.setAttribute("searchResults", searchResults);
        request.setAttribute("resultCount", searchResults.size());
        
        request.getRequestDispatcher("/search-results.jsp").forward(request, response);
    }
}
