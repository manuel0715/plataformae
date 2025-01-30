package com.plataformae.ws.service;

import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.GraficasResponseDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface IConfiguracionService {
    ResponseEntity<ApiResponseDTO<GraficasResponseDTO>> cargarGraficas(LocalDate fechaInicio, LocalDate fechaFin);
}
