package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.EstadoContacto;
import com.plataformae.ws.db.entity.EstadoProceso;
import com.plataformae.ws.db.repository.IEstadoContactoRepository;
import com.plataformae.ws.db.repository.IEstadoProcesoRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.service.IEstadoProcesoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class EstadoProcesoServiceImpl implements IEstadoProcesoService {


    private final IEstadoProcesoRepository iEstadoProcesoRepository;
    private final IEstadoContactoRepository iEstadoContactoRepository;

    public EstadoProcesoServiceImpl(IEstadoProcesoRepository iEstadoProcesoRepository, IEstadoContactoRepository iEstadoContactoRepository) {
        this.iEstadoProcesoRepository = iEstadoProcesoRepository;
        this.iEstadoContactoRepository = iEstadoContactoRepository;
    }


    @Override
    public ResponseEntity<ApiResponseDTO<List<EstadoProceso>>> obtenerEstadosProceso( Integer estadoContactoId) {
        Optional<EstadoContacto> estado = iEstadoContactoRepository.findById(estadoContactoId);

        List<EstadoProceso> list = estado.isPresent()
                ? iEstadoProcesoRepository.findByEstadoContacto(estado)
                : Collections.emptyList();

        return buildResponse("Ok", list, HttpStatus.OK);
    }
}
