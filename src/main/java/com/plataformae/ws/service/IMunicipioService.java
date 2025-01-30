package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Municipio;
import com.plataformae.ws.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMunicipioService {
    ResponseEntity<ApiResponseDTO<List<Municipio>>> obtenerMunicipio(Long departamentoId);
}
