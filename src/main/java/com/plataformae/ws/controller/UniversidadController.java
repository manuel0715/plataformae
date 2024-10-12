package com.plataformae.ws.controller;


import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.UniversidadDTO;
import com.plataformae.ws.service.IUniversidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/universidad")
public class UniversidadController {

    private final IUniversidadService universidadService;

    @Autowired
    public UniversidadController(IUniversidadService universidadService) {
        this.universidadService = universidadService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>>  buscarUniversidades(
            @RequestParam(value = "universidad", required = false) String universidad,
            @RequestParam(value = "ciudad", required = false) String ciudad,
            @RequestParam(value = "carrera", required = false) String carrera,
            @RequestParam(value = "sede", required = false) String sede) {

        return universidadService.buscarUniversidades(universidad, ciudad, carrera,sede);
    }

}
