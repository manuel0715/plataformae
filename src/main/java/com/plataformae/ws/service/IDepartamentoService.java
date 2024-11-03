package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Departamento;
import com.plataformae.ws.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDepartamentoService {
    ResponseEntity<ApiResponseDTO<List<Departamento>>> obtenerDepartamentos();
}
