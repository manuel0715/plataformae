package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.EstadoProceso;
import com.plataformae.ws.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEstadoProcesoService {
    ResponseEntity<ApiResponseDTO<List<EstadoProceso>>> obtenerEstadosProceso( Integer estadoContactoId);
}
