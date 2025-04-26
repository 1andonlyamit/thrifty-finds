# Thrifty Finds - Buy and Sell Application

Thrifty Finds is a beginner-level Java web application for buying and selling pre-loved items. The application is built using Java Servlets, JSP/JSTL for the frontend, and MySQL for the database.

## Features

- User Authentication (Register/Login)
- Product Listing and Management
- Product Search Functionality
- Image Upload for Products
- Responsive Design

## Technology Stack

- **Backend:** Java Servlets
- **Frontend:** JSP (JavaServer Pages), JSTL, HTML, CSS
- **Database:** MySQL
- **Build Tool:** Maven
- **Server:** Apache Tomcat 7

## Project Structure

The project follows the Model-View-Controller (MVC) architecture:

### Model
- `User.java` - Represents a user in the application
- `Product.java` - Represents a product listing

### View
- JSP pages in `src/main/webapp/`
- CSS styles in `src/main/webapp/css/`

### Controller
- `UserServlet.java` - Handles user registration and login
- `ProductServlet.java` - Handles product creation and management
- `HomeServlet.java` - Manages the home page
- `SearchServlet.java` - Handles product search
- `LogoutServlet.java` - Manages user logout

### Data Access Objects (DAO)
- `UserDAO.java` - Database operations for users
- `ProductDAO.java` - Database operations for products
- `DBConnection.java` - Database connection management
- `DBInitializer.java` - Database initialization

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- MySQL Server
- Maven

### Database Configuration
The application is configured to connect to a MySQL database with these default settings:
- Host: localhost
- Port: 3306
- Database Name: thriftyfinds
- Username: root
- Password: (empty)

You can modify these settings in `DBConnection.java` if needed.

### Build and Run
1. Make sure MySQL server is running
2. Build the project with Maven:
   ```
   mvn clean package
   ```
3. Run the application using Tomcat Maven plugin:
   ```
   mvn tomcat7:run
   ```
4. Access the application at http://localhost:5000

## Usage Guide

### Registration
1. Click "Register" on the navigation bar
2. Fill in the registration form with your details
3. Click "Register" to create your account

### Login
1. Click "Login" on the navigation bar
2. Enter your username and password
3. Click "Login" to access your account

### Adding a Product
1. Log in to your account
2. Click "Add Product" on the navigation bar
3. Fill in the product details (title, description, price)
4. Upload a product image
5. Click "Add Product" to list your item

### Searching Products
1. Use the search bar at the top of the page
2. Enter keywords related to the product you're looking for
3. Click "Search" to see matching results

### Viewing Product Details
1. Click on any product from the home page or search results
2. The product details page shows all information about the item
3. If you're the seller, you'll see options to edit or delete the listing

### Logging Out
1. Click "Logout" on the navigation bar to end your session

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

## Future Enhancements

- User profile management
- Product categories and filtering
- Shopping cart functionality
- Payment integration
- User ratings and reviews
- Direct messaging between users