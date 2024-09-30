package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Ciudad;
import com.plataformae.ws.db.entity.Sede;
import com.plataformae.ws.dto.ApiResponse;
import com.plataformae.ws.dto.SedeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISedeService {

    ResponseEntity<ApiResponse<List<SedeDTO>>> obtenerSedes(Long universidadId, Long ciudadId);
}
