package com.plataformae.ws.controller;

import com.plataformae.ws.dto.*;
import com.plataformae.ws.service.IInscripcionService;
import com.plataformae.ws.service.ITrazabilidadGestionInscripcionesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final IInscripcionService iInscripcionService;
    private final ITrazabilidadGestionInscripcionesService iTrazabilidadGestionInscripcionesService;

    public InscripcionController(IInscripcionService iInscripcionService, ITrazabilidadGestionInscripcionesService iTrazabilidadGestionInscripcionesService) {
        this.iInscripcionService = iInscripcionService;
        this.iTrazabilidadGestionInscripcionesService = iTrazabilidadGestionInscripcionesService;
    }

    @PostMapping(value = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO<InscripcionResponseDTO>> guardarInscripcion(@RequestBody InscripcionRequestDTO request) {

        return iInscripcionService.guardarInscripcion(request);
    }

    @PostMapping(value = "/cargar-inscripciones-usuario",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO<List<CargarInscripcionResponseDTO>>> cargarMisInscripciones() {

        return iInscripcionService.cargarMisInscripciones();
    }

    @GetMapping(value = "/cargar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO<List<CargarInscripcionResponseDTO>>> cargarInscripciones(@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                                                                                  @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
                                                                                                  @RequestParam(value = "estadoProcesoIds", required = false) List<Integer> estadoProcesoIds,
                                                                                                  @RequestParam(value = "estadoContactoIds", required = false) List<Integer> estadoContactoIds) {

        if (estadoProcesoIds == null) {
            estadoProcesoIds = new ArrayList<>();
        }
        if (estadoContactoIds == null) {
            estadoContactoIds = new ArrayList<>();
        }
        return iInscripcionService.cargarInscripciones(fechaInicio, fechaFin, estadoProcesoIds, estadoContactoIds);
    }

    @PostMapping(value = "/guardar-gestion", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO<String>> guardarGestionInscripcion(@RequestBody GuardarGestionIncripcionDTO request) {

        return iInscripcionService.guardarGestionInscripcion(request);
    }

    @GetMapping(value = "/cargar-trazabilidad-gestion",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO<List<TrazabilidadResponseDTO>>> cargarInscripciones(@RequestParam int incripcionId) {

        return iTrazabilidadGestionInscripcionesService.cargarTrazabilidad(incripcionId);

    }
}