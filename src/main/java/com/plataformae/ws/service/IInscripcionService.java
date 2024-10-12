package com.plataformae.ws.service;

import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.InscripcionRequestDTO;
import com.plataformae.ws.dto.InscripcionResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IInscripcionService {
    ResponseEntity<ApiResponseDTO<InscripcionResponseDTO>> guardarInscripcion(InscripcionRequestDTO request);
}
