package com.plataformae.ws.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class ImagenService {

    private static final Logger LOGGER = LogManager.getLogger(ImagenService.class);

    public String convertirImagenBase64(String imagePath){

        String response="";

        try {
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            response=Base64.getEncoder().encodeToString(imageBytes);
    } catch (IOException e) {
            LOGGER.error("Error conviertiento img to base64: {}", e.getMessage(), e);
    }
        return response;
    }
}
