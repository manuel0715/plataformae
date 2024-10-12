package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Ciudad;
import com.plataformae.ws.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICiudadService {
    ResponseEntity<ApiResponseDTO<List<Ciudad>>> obtenerTodasLasCiudades();
}
