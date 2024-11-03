package com.plataformae.ws.controller;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.SedeDTO;
import com.plataformae.ws.dto.UniversidadDTO;
import com.plataformae.ws.service.*;
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


    private final IMunicipioService iMunicipioService;
    private final ISedeService iSedeService;
    private final IUniversidadService iUniversidadService;
    private final IEstadoContactoService iEstadoContactoService;
    private final IEstadoProcesoService iEstadoProcesoService;
    private final IDepartamentoService iDepartamentoService;
    private final IGeneroService iGeneroService;

    @Autowired
    public ConfiguracionController(IMunicipioService iMunicipioService, ISedeService iSedeService, IUniversidadService iUniversidadService, IEstadoContactoService iEstadoContactoService, IEstadoProcesoService iEstadoProcesoService, IDepartamentoService iDepartamentoService, IGeneroService iGeneroService) {
        this.iMunicipioService = iMunicipioService;
        this.iSedeService = iSedeService;
        this.iUniversidadService = iUniversidadService;
        this.iEstadoContactoService = iEstadoContactoService;
        this.iEstadoProcesoService = iEstadoProcesoService;
        this.iDepartamentoService = iDepartamentoService;
        this.iGeneroService = iGeneroService;
    }

    @GetMapping("/municipios")
    public ResponseEntity<ApiResponseDTO<List<Municipio>>> obtenerMunicipios(@RequestParam  Integer departamentoId) {

        return iMunicipioService.obtenerMunicipio(departamentoId);
    }

    @GetMapping("/departamentos")
    public ResponseEntity<ApiResponseDTO<List<Departamento>>> obtenerDepartametos() {

        return iDepartamentoService.obtenerDepartamentos();
    }

    @GetMapping("/generos")
    public ResponseEntity<ApiResponseDTO<List<Genero>>> obtenerGeneros() {

        return iGeneroService.obtenerGenero();
    }

    @GetMapping("/sedes")
    public ResponseEntity<ApiResponseDTO<List<SedeDTO>>> obtenerSedes(@RequestParam(required = false) Long municipio ,
                                                                      @RequestParam(required = false) Long universidad) {
        return iSedeService.obtenerSedes(universidad,municipio);
    }

    @GetMapping("/universidades")
    public ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>> obtenerUniversidades() {

        return iUniversidadService.obtenerUniversidades();
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>>  buscarUniversidades(
            @RequestParam(value = "universidad", required = false) String universidad,
            @RequestParam(value = "municipio", required = false) String municipio,
            @RequestParam(value = "carrera", required = false) String carrera,
            @RequestParam(value = "sede", required = false) String sede) {

        return iUniversidadService.buscarUniversidades(universidad, municipio, carrera,sede);
    }

    @GetMapping("/estados-proceso")
    public ResponseEntity<ApiResponseDTO<List<EstadoProceso>>> obtenerEstadosProceso(@RequestParam  Integer estadoContactoId) {

        return iEstadoProcesoService.obtenerEstadosProceso(estadoContactoId);
    }

    @GetMapping("/estados-contacto")
    public ResponseEntity<ApiResponseDTO<List<EstadoContacto>>> obtenerEstadosContacto() {

        return iEstadoContactoService.obtenerEstadosContacto();
    }
}
