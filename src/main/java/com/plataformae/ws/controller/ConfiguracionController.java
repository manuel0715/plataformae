package com.plataformae.ws.controller;

import com.plataformae.ws.db.entity.Ciudad;
import com.plataformae.ws.db.entity.Sede;
import com.plataformae.ws.db.entity.Universidad;
import com.plataformae.ws.dto.ApiResponse;
import com.plataformae.ws.dto.SedeDTO;
import com.plataformae.ws.dto.UniversidadDTO;
import com.plataformae.ws.service.ICiudadService;
import com.plataformae.ws.service.ISedeService;
import com.plataformae.ws.service.IUniversidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/api/configuracion")
public class ConfiguracionController {


    private final ICiudadService iCiudadService;
    private final ISedeService iSedeService;
    private final IUniversidadService iUniversidadService;

    @Autowired
    public ConfiguracionController(ICiudadService iCiudadService,  ISedeService iSedeService, IUniversidadService iUniversidadService) {
        this.iCiudadService = iCiudadService;
        this.iSedeService = iSedeService;
        this.iUniversidadService = iUniversidadService;
    }

    @GetMapping("/ciudades")
    public ResponseEntity<ApiResponse<List<Ciudad>>> obtenerSedes() {

        return iCiudadService.obtenerTodasLasCiudades();
    }

    @GetMapping("/sedes")
    public ResponseEntity<ApiResponse<List<SedeDTO>>> obtenerSedes(@RequestParam(required = false) Long ciudad ,
                                                                   @RequestParam(required = false) Long universidad) {
        return iSedeService.obtenerSedes(universidad,ciudad);
    }

    @GetMapping("/universidades")
    public ResponseEntity<ApiResponse<List<UniversidadDTO>>> obtenerUniversidades() {

        return iUniversidadService.obtenerUniversidades();
    }
}
