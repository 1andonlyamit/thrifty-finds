package com.thriftyfinds.listener;

import com.thriftyfinds.dao.DBInitializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

/**
 * Application context listener for initialization tasks
 */
@WebListener
public class AppContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing Thrifty Finds application...");
        
        // Initialize database tables
        DBInitializer.initialize();
        
        // Create uploads directory if it doesn't exist
        String uploadsDir = sce.getServletContext().getRealPath("/uploads");
        File uploadsDirFile = new File(uploadsDir);
        if (!uploadsDirFile.exists()) {
            uploadsDirFile.mkdirs();
            System.out.println("Created uploads directory at: " + uploadsDir);
        }
        
        System.out.println("Thrifty Finds application initialized successfully");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Shutting down Thrifty Finds application...");
    }
}
