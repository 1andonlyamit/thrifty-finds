# Thrifty Finds Installation Guide

This guide provides step-by-step instructions for installing and setting up the Thrifty Finds application.

## System Requirements

- Java Development Kit (JDK) 11 or higher
- MySQL Server 5.7 or higher
- Maven 3.6 or higher
- A Java IDE like Eclipse, IntelliJ IDEA, or VS Code (optional)

## Step 1: Setting Up the Database

1. **Install MySQL Server** if you haven't already:
   - For Windows: Download and install from the [MySQL official website](https://dev.mysql.com/downloads/installer/)
   - For macOS: Use Homebrew with `brew install mysql`
   - For Linux: Use your package manager, e.g., `sudo apt install mysql-server`

2. **Start the MySQL service**:
   - Windows: Use the MySQL Workbench or command `net start mysql`
   - macOS/Linux: `sudo service mysql start` or `sudo systemctl start mysql`

3. **Create the database**:
   - Access MySQL command line:
     ```bash
     mysql -u root -p
     ```
   - Create the database:
     ```sql
     CREATE DATABASE thriftyfinds;
     USE thriftyfinds;
     ```
   - Exit MySQL with `exit;`

## Step 2: Getting the Source Code

1. **Clone the repository** (if using Git):
   ```bash
   git clone <repository-url>
   cd thrifty-finds
   ```

2. **Alternatively, download and extract** the project ZIP file:
   - Extract to a directory of your choice
   - Open a terminal/command prompt and navigate to that directory

## Step 3: Configuring the Database Connection

1. **Check the database configuration** in `src/main/java/com/thriftyfinds/dao/DBConnection.java`:
   ```java
   private static final String HOST = "localhost";
   private static final String PORT = "3306";
   private static final String DATABASE = "thriftyfinds";
   private static final String USER = "root";
   private static final String PASSWORD = "";
   ```

2. **Modify these values** if your MySQL setup is different:
   - If you're using a different username/password
   - If MySQL is running on a different port
   - If you named the database differently

## Step 4: Building and Running the Application

1. **Build the project** with Maven:
   ```bash
   mvn clean package
   ```

2. **Run the application** using the Tomcat Maven plugin:
   ```bash
   mvn tomcat7:run
   ```

3. **Access the application** in your web browser:
   ```
   http://localhost:5000
   ```

## Step 5: Verifying the Setup

1. **The first run** will automatically create the necessary database tables.
2. **Register a new user** to test the authentication system.
3. **Add a product** to test the product management functionality.
4. **Search for products** to test the search functionality.

## Troubleshooting

### Database Connection Issues

If you encounter database connection problems:

1. **Verify MySQL is running**:
   ```bash
   ps aux | grep mysql   # On Linux/macOS
   tasklist | findstr mysql   # On Windows
   ```

2. **Check database credentials** in `DBConnection.java` match your MySQL setup.

3. **Verify the database exists**:
   ```bash
   mysql -u root -p
   SHOW DATABASES;
   ```

### Application Fails to Start

If the Tomcat server fails to start:

1. **Check if port 5000 is already in use**:
   ```bash
   netstat -ano | findstr 5000   # On Windows
   lsof -i :5000   # On Linux/macOS
   ```

2. **Change the port** in `pom.xml` if needed:
   ```xml
   <configuration>
       <port>8080</port>   <!-- Change from 5000 to another port -->
       <path>/</path>
   </configuration>
   ```

### Compilation Errors

If you encounter compilation errors:

1. **Verify Java version** compatibility:
   ```bash
   java -version
   ```

2. **Ensure all dependencies** are properly downloaded:
   ```bash
   mvn dependency:resolve
   ```

## Deploying to a Production Environment

For deploying to a production server:

1. **Build the WAR file**:
   ```bash
   mvn clean package
   ```

2. **Deploy the WAR file** to your Tomcat/JEE server:
   - Copy the `target/thrifty-finds.war` file to your server's webapps directory
   - Or use your application server's deployment method

3. **Configure a production database**:
   - Create a separate production database
   - Update `DBConnection.java` with the production database details
   - Rebuild the application

## Security Considerations

For a production environment, consider:

1. **Password hashing**: Implement proper password hashing in `UserDAO.java`
2. **HTTPS**: Configure your server to use HTTPS
3. **Connection pooling**: Implement database connection pooling for better performance
4. **Environment variables**: Store database credentials in environment variables rather than hardcoding