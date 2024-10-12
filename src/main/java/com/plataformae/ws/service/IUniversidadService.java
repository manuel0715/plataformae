package com.plataformae.ws.service;

import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.UniversidadDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUniversidadService {

    public ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>> buscarUniversidades(String universidad, String ciudad, String carrera, String sedea);

    ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>> obtenerUniversidades();
}
