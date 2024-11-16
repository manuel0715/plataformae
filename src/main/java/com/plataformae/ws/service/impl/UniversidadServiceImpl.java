package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.db.repository.BuscarCarreraRepository;
import com.plataformae.ws.db.repository.IUniversidadRepository;
import com.plataformae.ws.dto.*;
import com.plataformae.ws.service.IUniversidadService;
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
    public ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>> obtenerUniversidades(String nit,String estado) {
        List<Universidad> universidades;


        if ((nit == null || nit.isBlank()) && estado == null ) {
            // Ambos parámetros son vacíos o nulos
            universidades = universidadRepository.findAll();
        } else if (nit != null && !nit.isBlank() && estado == null) {
            // Solo el NIT está presente
            universidades = universidadRepository.findByNit(nit);
        } else if ((nit == null || nit.isBlank()) && estado != null) {
            // Solo el estado está presente
            universidades = universidadRepository.findAllByEstado(estado);
        } else {
            // Ambos parámetros están presentes
            universidades = universidadRepository.findAllByNitAndEstado(nit, estado);
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
                convertirBase64(universidad.getTerminosCondiciones()),
                universidad.getDireccion(),
                universidad.getTelefono());
    }

    public ResponseEntity<ApiResponseDTO<List<CarreraUniversidadResponseDTO>>> buscarCarreras(String terminoBusqueda,Integer universidad, Integer modalidad) {
        List<CarreraUniversidadResponseDTO> list = buscarCarreraRepository.buscarCarreras(terminoBusqueda,universidad,modalidad);
        return buildResponse("Ok", list, HttpStatus.OK);
    }

}
