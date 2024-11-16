package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.db.repository.jpa.BuscarCarreraRepository;
import com.plataformae.ws.db.repository.jpa.IUniversidadRepository;
import com.plataformae.ws.dto.*;
import com.plataformae.ws.service.IUniversidadService;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;
import static com.plataformae.ws.util.Utils.convertirBase64;

@Service
public class UniversidadServiceImpl implements IUniversidadService {


    private final IUniversidadRepository universidadRepository;
    private final BuscarCarreraRepository buscarCarreraRepository;


    public UniversidadServiceImpl(IUniversidadRepository universidadRepository, BuscarCarreraRepository buscarCarreraRepository) {
        this.universidadRepository = universidadRepository;
        this.buscarCarreraRepository = buscarCarreraRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>> obtenerUniversidades(String nit) {
        List<Universidad> universidades;
        if (nit == null || nit.isEmpty()) {
             universidades = universidadRepository.findAllByEstado("");
        }else{
            universidades=universidadRepository.findByNit(nit);
        }
        // Convertimos las entidades a DTO sin municipio
        List<UniversidadDTO> universidadDTOs = universidades.stream()
                .map(this::convertirAUniversidadDTO)
                .toList();

        return buildResponse("Ok", universidadDTOs, HttpStatus.OK);
    }


    private UniversidadDTO convertirAUniversidadDTO(Universidad universidad) {
        return new UniversidadDTO(
                universidad.getId(),
                universidad.getEstado(),
                universidad.getNombre(),
                universidad.getNit(),
                universidad.getTipoUniversidad(),
                convertirBase64(universidad.getLogo()),
                convertirBase64(universidad.getTerminosCondiciones()));
    }

    public ResponseEntity<ApiResponseDTO<List<CarreraUniversidadResponseDTO>>> buscarCarreras(String terminoBusqueda,Integer universidad) {
        List<CarreraUniversidadResponseDTO> list = buscarCarreraRepository.buscarCarreras(terminoBusqueda,universidad);
        return buildResponse("Ok", list, HttpStatus.OK);
    }

}
