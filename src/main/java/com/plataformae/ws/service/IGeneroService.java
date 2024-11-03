package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Genero;
import com.plataformae.ws.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGeneroService {

    ResponseEntity<ApiResponseDTO<List<Genero>>> obtenerGenero();
}
