package com.thriftyfinds.controller;

import com.thriftyfinds.dao.UserDAO;
import com.thriftyfinds.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet handling user registration and login
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/register", "/login"})
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        if ("/register".equals(path)) {
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else if ("/login".equals(path)) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        if ("/register".equals(path)) {
            handleRegistration(request, response);
        } else if ("/login".equals(path)) {
            handleLogin(request, response);
        }
    }
    
    /**
     * Handles user registration
     */
    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullName = request.getParameter("fullName");
        
        // Validate input data
        boolean hasError = false;
        
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("usernameError", "Username is required");
            hasError = true;
        } else if (userDAO.usernameExists(username)) {
            request.setAttribute("usernameError", "Username already exists");
            hasError = true;
        }
        
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            request.setAttribute("emailError", "Valid email is required");
            hasError = true;
        } else if (userDAO.emailExists(email)) {
            request.setAttribute("emailError", "Email already exists");
            hasError = true;
        }
        
        if (password == null || password.length() < 6) {
            request.setAttribute("passwordError", "Password must be at least 6 characters");
            hasError = true;
        }
        
        if (confirmPassword == null || !confirmPassword.equals(password)) {
            request.setAttribute("confirmPasswordError", "Passwords do not match");
            hasError = true;
        }
        
        if (fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("fullNameError", "Full name is required");
            hasError = true;
        }
        
        if (hasError) {
            // Keep the input values
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("fullName", fullName);
            
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        // If validation passes, register the user
        User user = new User(username, email, password, fullName);
        boolean isRegistered = userDAO.registerUser(user);
        
        if (isRegistered) {
            // Registration successful, redirect to login
            request.getSession().setAttribute("message", "Registration successful! Please login.");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // Registration failed
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            
            // Keep the input values
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("fullName", fullName);
            
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
    
    /**
     * Handles user login
     */
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate input data
        boolean hasError = false;
        
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("usernameError", "Username is required");
            hasError = true;
        }
        
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("passwordError", "Password is required");
            hasError = true;
        }
        
        if (hasError) {
            // Keep the username
            request.setAttribute("username", username);
            
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        // If validation passes, authenticate the user
        User user = userDAO.authenticateUser(username, password);
        
        if (user != null) {
            // Authentication successful, create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            
            // Redirect to home page
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Authentication failed
            request.setAttribute("errorMessage", "Invalid username or password");
            
            // Keep the username
            request.setAttribute("username", username);
            
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
