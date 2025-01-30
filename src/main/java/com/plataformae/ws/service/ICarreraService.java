package com.plataformae.ws.service;


import com.plataformae.ws.db.entity.Carrera;
import com.plataformae.ws.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICarreraService {

    ResponseEntity<ApiResponseDTO<List<Carrera>>> obtenerCarreras();
}
