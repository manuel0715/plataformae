package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Ciudad;
import com.plataformae.ws.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICiudadService {
    ResponseEntity<ApiResponse<List<Ciudad>>> obtenerTodasLasCiudades();
}
