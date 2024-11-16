package com.plataformae.ws.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.ApiResponsePageDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Utils {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger LOGGER = LogManager.getLogger(Utils.class);
    static {
        mapper.registerModule(new JavaTimeModule());
    }

    private Utils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static  <T> ResponseEntity<ApiResponseDTO<T>> buildResponse(String message, T data, HttpStatus status) {
        ApiResponseDTO<T> response = new ApiResponseDTO<>(message, data, status.value());
        return new ResponseEntity<>(response, status);
    }

    public static  <T> ResponseEntity<ApiResponsePageDTO<T>> buildResponsePage(String message, T data, HttpStatus status, long page, int size) {
        ApiResponsePageDTO<T> response = new ApiResponsePageDTO<>(message, data, status.value(),page,size);
        return new ResponseEntity<>(response, status);
    }

    public static String objectToJsonString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error al convertir objeto a JSON: {}", e.getMessage(), e);
            return "{}";
        }
    }

    public static String maskEmail(String email) {
        String[] partes = email.split("@");
        String nombreUsuario = partes[0];
        String dominio = partes[1];

        if (nombreUsuario.length() > 3) {
            nombreUsuario = nombreUsuario.substring(0, 3) + "***";
        } else {
            nombreUsuario = nombreUsuario.charAt(0) + "***";
        }

        return nombreUsuario + "@" + dominio.toLowerCase();
    }

    public static String maskPhoneNumber(String phoneNumber) {
               // Enmascarar los primeros dígitos y mostrar solo los últimos 4
        return phoneNumber.replaceAll(".(?=.{4})", "*");
    }

    public static String convertirBase64(String imagePath) {
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
