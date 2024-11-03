package com.plataformae.ws.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class Base64Service {

    private static final Logger LOGGER = LogManager.getLogger(Base64Service.class);

    public String convertirBase64(String imagePath) {
        String response = "";
        String mimeType = "";

        try {
            // Obtener el tipo MIME del archivo
            mimeType = Files.probeContentType(Paths.get(imagePath));

            // Leer el archivo y convertir a Base64
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            response = Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            LOGGER.error("Error convirtiendo el archivo a Base64: {}", e.getMessage(), e);
        }

        // Devolver tanto el tipo MIME como el contenido en Base64
        return "data:" + mimeType + ";base64," + response;
    }
}