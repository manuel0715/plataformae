package com.plataformae.ws.controller;

import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.GestionUniversidadDTO;
import com.plataformae.ws.service.IUniversidadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/universidad")
public class UniversidadController {


    private final IUniversidadService iUniversidadService;

    public UniversidadController(IUniversidadService iUniversidadService) {
        this.iUniversidadService = iUniversidadService;
    }

    @PostMapping(value = "/gestionar-universidad", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO<String>> guardarGestionInscripcion(@RequestBody GestionUniversidadDTO request) {
        return iUniversidadService.upsertUniversidad(request);
    }
}
