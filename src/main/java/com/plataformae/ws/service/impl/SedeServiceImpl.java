package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.db.repository.ISedeRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.CarreraDTO;
import com.plataformae.ws.dto.SedeDTO;
import com.plataformae.ws.service.ISedeService;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class SedeServiceImpl implements ISedeService {

    private final ISedeRepository iSedeRepository;

    public SedeServiceImpl(ISedeRepository iSedeRepository) {
        this.iSedeRepository = iSedeRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<ApiResponseDTO<List<SedeDTO>>> obtenerSedes(Long universidadId, Long municipioId) {
        Specification<Sede> spec = Specification.where(null);

        // Filtro por universidad
        if (universidadId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Sede, RelUniversidadMunicipio> relJoin = root.join("relUniversidadMunicipio");
                Join<RelUniversidadMunicipio, Universidad> universidadJoin = relJoin.join("universidad");
                return criteriaBuilder.equal(universidadJoin.get("id"), universidadId);
            });
        }

        // Filtro por Municipio
        if (municipioId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Sede, RelUniversidadMunicipio> relJoin = root.join("relUniversidadMunicipio");
                Join<RelUniversidadMunicipio, Municipio> municipioJoin = relJoin.join("municipio");
                return criteriaBuilder.equal(municipioJoin.get("id"), municipioId);
            });
        }

        // Obtener las sedes que cumplen con los filtros
        List<Sede> sedes = iSedeRepository.findAll(spec);

        // Convertir a DTOs
        List<SedeDTO> sedeDTOs = sedes.stream()
                .map(sede -> {
                    SedeDTO sedeDTO = new SedeDTO();
                    sedeDTO.setId(sede.getId());
                    sedeDTO.setEstado(sede.getEstado());
                    sedeDTO.setNombre(sede.getNombre());
                    sedeDTO.setDireccion(sede.getDireccion());

                    // Mapear las carreras asociadas
                    List<CarreraDTO> carreraDTOs = sede.getRelSedeCarrera().stream()
                            .map(relSedeCarrera -> {
                                Carrera carrera = relSedeCarrera.getCarrera();
                                CarreraDTO carreraDTO = new CarreraDTO();
                                carreraDTO.setId(carrera.getId());
                                carreraDTO.setEstado(carrera.getEstado());
                                carreraDTO.setNombre(carrera.getNombre());
                                carreraDTO.setDuracion(carrera.getDuracion());
                                return carreraDTO;
                            }).toList();

                    sedeDTO.setCarreras(carreraDTOs);
                    return sedeDTO;
                }).toList();

        return buildResponse("Ok", sedeDTOs, HttpStatus.OK);
    }

}
