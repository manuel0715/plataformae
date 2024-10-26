package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.EstadoContacto;
import com.plataformae.ws.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEstadoContactoService {
    ResponseEntity<ApiResponseDTO<List<EstadoContacto>>> obtenerEstadosContacto();
}
