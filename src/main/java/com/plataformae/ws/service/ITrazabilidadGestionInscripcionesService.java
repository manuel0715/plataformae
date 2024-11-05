package com.plataformae.ws.service;

import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.TrazabilidadResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITrazabilidadGestionInscripcionesService {
    ResponseEntity<ApiResponseDTO<List<TrazabilidadResponseDTO>>> cargarTrazabilidad(int incripcionId);
}
