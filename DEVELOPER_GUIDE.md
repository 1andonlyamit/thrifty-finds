# Thrifty Finds Developer Guide

This guide provides detailed information about the Thrifty Finds application architecture, code structure, and how to extend the application with new features.

## Architecture Overview

Thrifty Finds follows the Model-View-Controller (MVC) architecture:

1. **Model**: Java classes representing the core objects (User, Product)
2. **View**: JSP pages and CSS for presentation
3. **Controller**: Servlet classes that handle HTTP requests
4. **Data Access**: DAO classes that interact with the database

## Directory Structure

```
src/main/
├── java/
│   └── com/
│       └── thriftyfinds/
│           ├── controller/    # Servlet classes
│           ├── dao/           # Database Access Objects
│           ├── listener/      # Application listeners
│           ├── model/         # Model classes
│           └── util/          # Utility classes
└── webapp/
    ├── WEB-INF/
    │   ├── includes/          # Reusable JSP components
    │   └── web.xml            # Web application configuration
    ├── css/                   # CSS stylesheets
    ├── uploads/               # Directory for uploaded images
    └── *.jsp                  # JSP view pages
```

## Package Descriptions

### com.thriftyfinds.model

Contains the core domain objects:

- `User.java`: Represents a registered user
- `Product.java`: Represents a product listing

### com.thriftyfinds.dao

Data Access Objects for database operations:

- `DBConnection.java`: Manages database connections
- `DBInitializer.java`: Creates database tables if they don't exist
- `UserDAO.java`: CRUD operations for users
- `ProductDAO.java`: CRUD operations for products

### com.thriftyfinds.controller

Servlet classes that handle HTTP requests:

- `UserServlet.java`: Handles user registration and login
- `ProductServlet.java`: Handles product creation and management
- `HomeServlet.java`: Manages the home page
- `SearchServlet.java`: Handles product search
- `LogoutServlet.java`: Manages user logout

### com.thriftyfinds.listener

- `AppContextListener.java`: Initializes application resources when the app starts

### com.thriftyfinds.util

- `ImageUtil.java`: Utility methods for image handling

## Key Components

### Database Connection

Database connections are managed through the `DBConnection` singleton:

```java
// Get a database connection
Connection conn = DBConnection.getInstance().getConnection();

// Use the connection for your queries...

// Close the connection when done
DBConnection.getInstance().closeConnection(conn);
```

### Authentication Flow

1. User submits login form to `UserServlet` (/login)
2. `UserServlet.handleLogin()` validates credentials
3. If valid, user object is stored in session
4. User is redirected to home page
5. Protected resources check for user session attribute

### Request Processing

Most request processing follows this pattern:

1. Client sends request to a servlet
2. Servlet's `doGet` or `doPost` method determines the action
3. Action-specific handler method is called
4. Result is forwarded to appropriate JSP or redirected

## Database Schema

### Users Table

```sql
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  full_name VARCHAR(100) NOT NULL,
  registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Products Table

```sql
CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  description TEXT,
  price DECIMAL(10,2) NOT NULL,
  image_filename VARCHAR(255),
  seller_id INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_available BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (seller_id) REFERENCES users(id)
);
```

## How to Extend

### Adding a New Model

1. Create a new class in the `com.thriftyfinds.model` package
2. Define fields, constructors, getters, and setters
3. Implement `toString()` for debugging

Example:
```java
package com.thriftyfinds.model;

import java.util.Date;

public class Category {
    private int id;
    private String name;
    private String description;
    
    // Constructors, getters, setters...
}
```

### Adding a New DAO

1. Create a new class in the `com.thriftyfinds.dao` package
2. Implement CRUD methods using `DBConnection`
3. Add any required SQL statements

Example:
```java
package com.thriftyfinds.dao;

import com.thriftyfinds.model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public boolean addCategory(Category category) {
        // Implementation...
    }
    
    public Category getCategoryById(int id) {
        // Implementation...
    }
    
    // Other methods...
}
```

### Adding a New Servlet

1. Create a new class in the `com.thriftyfinds.controller` package
2. Extend `HttpServlet` and implement `doGet` and/or `doPost`
3. Register the servlet in `web.xml` or use `@WebServlet` annotation

Example:
```java
package com.thriftyfinds.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/categories", "/category/*"})
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Implementation...
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Implementation...
    }
}
```

### Adding a New JSP Page

1. Create a new JSP file in the `src/main/webapp` directory
2. Include header and footer with JSP includes
3. Use JSTL for dynamic content

Example:
```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Categories - Thrifty Finds</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />
    
    <main class="container">
        <h1>Categories</h1>
        
        <ul class="categories-list">
            <c:forEach items="${categories}" var="category">
                <li>
                    <a href="${pageContext.request.contextPath}/category/${category.id}">
                        ${category.name}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </main>
    
    <jsp:include page="/WEB-INF/includes/footer.jsp" />
</body>
</html>
```

## Testing Your Changes

1. **Unit Testing**: Add JUnit tests for core logic
2. **Integration Testing**: Test database operations with a test database
3. **Manual Testing**: Verify UI changes and interactions in the browser

## Building and Deployment

1. **Local Development**:
   ```bash
   mvn clean package tomcat7:run
   ```

2. **Building for Deployment**:
   ```bash
   mvn clean package
   ```
   This creates a WAR file in the `target` directory.

## Security Considerations

When extending the application, keep these security aspects in mind:

1. **Input Validation**: Validate all user inputs server-side
2. **SQL Injection**: Use PreparedStatements for all database queries
3. **XSS Prevention**: Use JSTL's `<c:out>` for displaying user inputs
4. **CSRF Protection**: Implement CSRF tokens for form submissions
5. **Authentication**: Ensure new features respect authentication requirements

## Performance Optimization

1. **Connection Pooling**: Consider adding connection pooling for better performance
2. **Pagination**: Implement pagination for lists with many items
3. **Caching**: Add caching for frequently accessed static data
4. **Image Optimization**: Resize and compress uploaded images