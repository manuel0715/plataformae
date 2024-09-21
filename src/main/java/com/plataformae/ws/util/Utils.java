package com.plataformae.ws.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.plataformae.ws.dto.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger LOGGER = LogManager.getLogger(Utils.class);
    static {
        mapper.registerModule(new JavaTimeModule());
    }

    private Utils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static  <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, T data, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(message, data, status.value());
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
}