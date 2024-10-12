package com.plataformae.ws.service;

import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.SedeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISedeService {

    ResponseEntity<ApiResponseDTO<List<SedeDTO>>> obtenerSedes(Long universidadId, Long ciudadId);
}
