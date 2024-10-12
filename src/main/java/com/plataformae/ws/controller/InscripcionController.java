package com.plataformae.ws.controller;

import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.InscripcionRequestDTO;
import com.plataformae.ws.dto.InscripcionResponseDTO;
import com.plataformae.ws.service.IInscripcionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final IInscripcionService iInscripcionService;

    public InscripcionController(IInscripcionService iInscripcionService) {
        this.iInscripcionService = iInscripcionService;
    }

    @PostMapping(value = "/guardar" ,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO<InscripcionResponseDTO>> guardarInscripcion(@RequestBody InscripcionRequestDTO request) {

        return iInscripcionService.guardarInscripcion(request);
    }
}
