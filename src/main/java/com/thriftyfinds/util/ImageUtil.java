package com.thriftyfinds.util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Utility class for handling image uploads
 */
public class ImageUtil {
    
    /**
     * Saves an uploaded image to the specified directory
     * @param part File part from multipart request
     * @param uploadDir Directory to save the image
     * @param fileName File name to use for the image
     * @throws IOException if an I/O error occurs
     */
    public static void saveImage(Part part, String uploadDir, String fileName) throws IOException {
        // Create upload directory if it doesn't exist
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
        
        // Save the file
        try (InputStream input = part.getInputStream()) {
            Files.copy(
                input, 
                Paths.get(uploadDir + File.separator + fileName),
                StandardCopyOption.REPLACE_EXISTING
            );
        }
    }
}
