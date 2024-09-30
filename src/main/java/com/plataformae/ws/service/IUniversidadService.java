package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Universidad;
import com.plataformae.ws.dto.ApiResponse;
import com.plataformae.ws.dto.UniversidadDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUniversidadService {

    public ResponseEntity<ApiResponse<List<UniversidadDTO>>> buscarUniversidades(String universidad, String ciudad, String carrera, String sedea);

    ResponseEntity<ApiResponse<List<UniversidadDTO>>> obtenerUniversidades();
}
