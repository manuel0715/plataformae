package com.plataformae.ws.service;

import com.plataformae.ws.dto.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface IInscripcionService {
    ResponseEntity<ApiResponseDTO<InscripcionResponseDTO>> guardarInscripcion(InscripcionRequestDTO request);

    ResponseEntity<ApiResponseDTO<List<CargarInscripcionResponseDTO>>> cargarMisInscripciones();

    ResponseEntity<ApiResponseDTO<List<CargarInscripcionResponseDTO>>> cargarInscripciones(LocalDate fechaInicio, LocalDate fechaFin, List<Integer> estadoProcesoIds, List<Integer> estadoContactoIds);

    ResponseEntity<ApiResponseDTO<String>> guardarGestionInscripcion(GuardarGestionIncripcionDTO request);
}
