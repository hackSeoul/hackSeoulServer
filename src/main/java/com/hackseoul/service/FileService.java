package com.hackseoul.service;
import org.springframework.util.Base64Utils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    private static final String UPLOAD_DIR = "uploads/";

    public String saveBase64File(String base64Data, String originalFileName) throws IOException {

        String base64Prefix = "data:image/jpg;base64,";
        String substring = base64Data.substring(base64Prefix.length());

        // Base64 decoding
        byte[] decodedBytes = Base64Utils.decodeFromString(substring);

        // Set the file extension to .png
        String extension = ".png";

        // Generate a unique file name using UUID and the .png extension
        String uniqueFileName = UUID.randomUUID().toString() + extension;

        // Define the file save path
        Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
        File file = path.toFile();

        // Create the directory if it doesn't exist
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // Write data to the file
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        }

        // Return the absolute path of the saved file
        return path.toAbsolutePath().toString();
    }

}

