package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.repository.jpa.ITrazabilidadGestionInscripcionesRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.TrazabilidadResponseDTO;
import com.plataformae.ws.service.ITrazabilidadGestionInscripcionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class TrazabilidadGestionInscripcionesServiceImpl implements ITrazabilidadGestionInscripcionesService {

    private final ITrazabilidadGestionInscripcionesRepository trazabilidadGestionInscripcionesRepository;

    public TrazabilidadGestionInscripcionesServiceImpl(ITrazabilidadGestionInscripcionesRepository trazabilidadGestionInscripcionesRepository) {
        this.trazabilidadGestionInscripcionesRepository = trazabilidadGestionInscripcionesRepository;
    }

    @Override
    public ResponseEntity<ApiResponseDTO<List<TrazabilidadResponseDTO>>> cargarTrazabilidad(int incripcionId) {

        List<TrazabilidadResponseDTO> trazabilidad = trazabilidadGestionInscripcionesRepository.cargarTrazabilidad(incripcionId);

        return buildResponse("Ok",trazabilidad, HttpStatus.OK);
    }
}
