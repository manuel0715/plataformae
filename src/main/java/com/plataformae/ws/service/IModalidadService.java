package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Modalidad;
import com.plataformae.ws.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IModalidadService {

    ResponseEntity<ApiResponseDTO<List<Modalidad>>> obtenerModalidad();
}
