package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Carrera;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.CarreraUniversidadResponseDTO;
import com.plataformae.ws.dto.UniversidadDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUniversidadService {

    ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>> obtenerUniversidades(String nit);

    ResponseEntity<ApiResponseDTO<List<CarreraUniversidadResponseDTO>>> buscarCarreras(String filtro,Integer universidad);
}
