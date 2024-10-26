package com.plataformae.ws.util.exeptions;

import com.plataformae.ws.dto.ApiResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import static com.plataformae.ws.util.Utils.buildResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleRuntimeException(RuntimeException ex, WebRequest request) {

        return buildResponse(ex.getMessage(),
                null,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleDataIException(Exception ex, WebRequest request) {

        return buildResponse("Ocurrió un error inesperado. Por favor intente de nuevo más tarde.",
                request,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleDataIDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {

        return buildResponse("Ocurrió un error inesperado. Por favor intente de nuevo más tarde.",
                request,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiResponseDTO<Object>> handleExpiredJwtException(ExpiredJwtException ex,WebRequest request) {
        return buildResponse("Token Expirado",
                request,
                HttpStatus.UNAUTHORIZED
        );
    }
}