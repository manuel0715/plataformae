package com.plataformae.ws.controller;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.CarreraUniversidadResponseDTO;
import com.plataformae.ws.dto.GraficasResponseDTO;
import com.plataformae.ws.dto.UniversidadDTO;
import com.plataformae.ws.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;
import static com.plataformae.ws.util.Utils.convertirBase64;


@RestController
@RequestMapping("/api/configuracion")
public class ConfiguracionController {

    @Value("${parameters.path-documentos.terminos-y-condiciones.plataforma-e}")
    private String pathTerminosYCondicionesPlataformae;

    private final IMunicipioService iMunicipioService;
    private final IUniversidadService iUniversidadService;
    private final IEstadoContactoService iEstadoContactoService;
    private final IEstadoProcesoService iEstadoProcesoService;
    private final IDepartamentoService iDepartamentoService;
    private final IGeneroService iGeneroService;
    private final IModalidadService iModalidadService;
    private final ICarreraService iCarreraService;
    private final IConfiguracionService iConfiguracionService;

    @Autowired
    public ConfiguracionController(IMunicipioService iMunicipioService, IUniversidadService iUniversidadService, IEstadoContactoService iEstadoContactoService, IEstadoProcesoService iEstadoProcesoService, IDepartamentoService iDepartamentoService, IGeneroService iGeneroService, IModalidadService iModalidadService, ICarreraService iCarreraService, IConfiguracionService iConfiguracionService) {
        this.iMunicipioService = iMunicipioService;
        this.iUniversidadService = iUniversidadService;
        this.iEstadoContactoService = iEstadoContactoService;
        this.iEstadoProcesoService = iEstadoProcesoService;
        this.iDepartamentoService = iDepartamentoService;
        this.iGeneroService = iGeneroService;
        this.iModalidadService = iModalidadService;
        this.iCarreraService = iCarreraService;
        this.iConfiguracionService = iConfiguracionService;
    }

    @GetMapping("/public/municipios")
    public ResponseEntity<ApiResponseDTO<List<Municipio>>> obtenerMunicipios(@RequestParam  Long departamentoId) {

        return iMunicipioService.obtenerMunicipio(departamentoId);
    }

    @GetMapping("/public/departamentos")
    public ResponseEntity<ApiResponseDTO<List<Departamento>>> obtenerDepartametos() {

        return iDepartamentoService.obtenerDepartamentos();
    }

    @GetMapping("/public/generos")
    public ResponseEntity<ApiResponseDTO<List<Genero>>> obtenerGeneros() {

        return iGeneroService.obtenerGenero();
    }

    @GetMapping("/universidades")
    public ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>> obtenerUniversidades( @RequestParam(required = false) String nit,
                                                                                      @RequestParam(required = false) String estado) {

        return iUniversidadService.obtenerUniversidades(nit,estado);
    }

    @GetMapping("/estados-proceso")
    public ResponseEntity<ApiResponseDTO<List<EstadoProceso>>> obtenerEstadosProceso(@RequestParam  Integer estadoContactoId) {

        return iEstadoProcesoService.obtenerEstadosProceso(estadoContactoId);
    }

    @GetMapping("/estados-contacto")
    public ResponseEntity<ApiResponseDTO<List<EstadoContacto>>> obtenerEstadosContacto() {

        return iEstadoContactoService.obtenerEstadosContacto();
    }

    @GetMapping("/public/terminos-y-condiciones")
    public ResponseEntity<ApiResponseDTO<String>> obtenerTerminosYCondiciones() {

        return buildResponse("Ok", convertirBase64(pathTerminosYCondicionesPlataformae), HttpStatus.OK);
    }

    @GetMapping("public/programas-academicos")
    public ResponseEntity<ApiResponseDTO<List<CarreraUniversidadResponseDTO>>>  buscarCarreras(
            @RequestParam(value = "filtro", required = false) String filtro,
            @RequestParam(value = "universidad", required = false) Integer  universidad,
            @RequestParam(value = "modalidad", required = false) Integer  modalidad,
            @RequestParam(value = "municipio", required = false) Integer  municipio) {

        return iUniversidadService.buscarCarreras(filtro,universidad,modalidad,municipio);
    }

    @GetMapping("/modalidad")
    public ResponseEntity<ApiResponseDTO<List<Modalidad>>> obtenerModalidad() {

        return iModalidadService.obtenerModalidad();
    }

    @GetMapping("/public/carreras")
    public ResponseEntity<ApiResponseDTO<List<Carrera>>> obtenerCarreras() {
        return iCarreraService.obtenerCarreras();
    }

    @GetMapping("/graficas")
    public ResponseEntity<ApiResponseDTO<GraficasResponseDTO>> cargarGraficas(@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                                                                    @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return iConfiguracionService.cargarGraficas(fechaInicio,fechaFin);
    }
}
