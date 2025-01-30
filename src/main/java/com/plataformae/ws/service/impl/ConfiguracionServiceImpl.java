package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.repository.IInscripcionesRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.GraficasResponseDTO;
import com.plataformae.ws.service.IConfiguracionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class ConfiguracionServiceImpl implements IConfiguracionService {

    private final IInscripcionesRepository inscripcionesRepository;

    public ConfiguracionServiceImpl(IInscripcionesRepository inscripcionesRepository) {
        this.inscripcionesRepository = inscripcionesRepository;
    }

    @Override
    public ResponseEntity<ApiResponseDTO<GraficasResponseDTO>> cargarGraficas(LocalDate inicio, LocalDate fin) {

        LocalDateTime fechaInicio = inicio.atStartOfDay(); // Inicio del día
        LocalDateTime fechaFin = fin.atTime(23, 59, 59);

        GraficasResponseDTO response = new GraficasResponseDTO();

        response.setTotalInscripciones(inscripcionesRepository.getCantidadIncripciones(fechaInicio, fechaFin));
        response.setMunicipios(inscripcionesRepository.getEstadisticasPorMunicipio(fechaInicio, fechaFin));
        response.setUniversidades(inscripcionesRepository.getEstadisticasPorUniversidad(fechaInicio, fechaFin));
        response.setEstadoProceso(inscripcionesRepository.getEstadisticasPorEstadoProceso(fechaInicio, fechaFin));
        response.setEstadoContacto(inscripcionesRepository.getEstadisticasPorEstadoContacto(fechaInicio, fechaFin));
        response.setCarreras(inscripcionesRepository.getEstadisticasPorCarrera(fechaInicio, fechaFin));

        return buildResponse("Estadísticas obtenidas con éxito", response, HttpStatus.OK);

    }
}
