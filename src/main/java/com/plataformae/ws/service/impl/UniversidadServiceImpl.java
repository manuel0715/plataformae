package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.db.repository.IUniversidadRepository;
import com.plataformae.ws.dto.*;
import com.plataformae.ws.service.IUniversidadService;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class UniversidadServiceImpl implements IUniversidadService {


    private final IUniversidadRepository universidadRepository;

    public UniversidadServiceImpl(IUniversidadRepository universidadRepository) {
        this.universidadRepository = universidadRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<List<UniversidadDTO>>> buscarUniversidades(String universidad, String ciudad, String carr, String sed) {
        Specification<Universidad> spec = Specification.where(null);

        // Filtro por nombre de universidad
        if (universidad != null && !universidad.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("nombre")), "%" + universidad.toUpperCase() + "%"));
        }

        // Filtro por sede
        if (sed != null && !sed.isEmpty()) {
            Long sedeId = Long.valueOf(sed);
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Universidad, RelUniversidadCiudad> relJoin = root.join("relUniversidadCiudad");
                Join<RelUniversidadCiudad, Sede> sedeJoin = relJoin.join("sede");
                return criteriaBuilder.equal(sedeJoin.get("id"), sedeId);
            });
        }

        // Filtro por ID de ciudad
        if (ciudad != null && !ciudad.isEmpty()) {
            Long ciudadId = Long.valueOf(ciudad);
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Universidad, RelUniversidadCiudad> relJoin = root.join("relUniversidadCiudad");
                Join<RelUniversidadCiudad, Ciudad> ciudadJoin = relJoin.join("ciudad");
                return criteriaBuilder.equal(ciudadJoin.get("id"), ciudadId);
            });
        }

        // Filtro por carrera
        if (carr != null && !carr.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Universidad, RelUniversidadCiudad> relJoin = root.join("relUniversidadCiudad");
                Join<RelUniversidadCiudad, Sede> sedeJoin = relJoin.join("sede");
                Join<Sede, RelSedeCarrera> relSedeCarreraJoin = sedeJoin.join("relSedeCarrera");
                Join<RelSedeCarrera, Carrera> carreraJoin = relSedeCarreraJoin.join("carrera");
                query.distinct(true); // Evita duplicados
                return criteriaBuilder.like(
                        criteriaBuilder.upper(carreraJoin.get("nombre")),
                        "%" + carr.toUpperCase() + "%"
                );
            });
        }

        List<Universidad> universidades = universidadRepository.findAll(spec);

        String carrUpper = (carr != null) ? carr.toUpperCase() : null;

        // Mapear a DTOs con filtro de carreras
        List<UniversidadDTO> universidadDTOs = universidades.stream()
                .map(uni -> {
                    UniversidadDTO universidadDTO = new UniversidadDTO();
                    universidadDTO.setId(uni.getId());
                    universidadDTO.setEstado(uni.getEstado());
                    universidadDTO.setNombre(uni.getNombre());
                    universidadDTO.setNit(uni.getNit());
                    universidadDTO.setTipoUniversidad(uni.getTipoUniversidad());

                    // Mapear ciudades
                    List<CiudadDTO> ciudadDTOs = uni.getRelUniversidadCiudades().stream()
                            .map(rel -> {
                                CiudadDTO ciudadDTO = new CiudadDTO();
                                ciudadDTO.setId(rel.getCiudad().getId());
                                ciudadDTO.setEstado(rel.getCiudad().getEstado());
                                ciudadDTO.setNombre(rel.getCiudad().getNombre());
                                ciudadDTO.setCodigoPostal(rel.getCiudad().getCodigoPostal());
                                ciudadDTO.setAbreviatura(rel.getCiudad().getAbreviatura());

                                // Mapear sedes
                                List<SedeDTO> sedeDTOs = rel.getSedes().stream()
                                        .map(sede -> {
                                            SedeDTO sedeDTO = new SedeDTO();
                                            sedeDTO.setId(sede.getId());
                                            sedeDTO.setEstado(sede.getEstado());
                                            sedeDTO.setNombre(sede.getNombre());
                                            sedeDTO.setDireccion(sede.getDireccion());

                                            // Mapear carreras con filtro
                                            List<CarreraDTO> carreraDTOs = sede.getRelSedeCarreras().stream()
                                                    .map(RelSedeCarrera::getCarrera)
                                                    .filter(carreraEntity -> {
                                                        if (carrUpper == null || carrUpper.isEmpty()) {
                                                            return true;
                                                        }
                                                        return carreraEntity.getNombre().toUpperCase().contains(carrUpper);
                                                    })
                                                    .map(carrera -> {
                                                        CarreraDTO carreraDTO = new CarreraDTO();
                                                        carreraDTO.setId(carrera.getId());
                                                        carreraDTO.setEstado(carrera.getEstado());
                                                        carreraDTO.setNombre(carrera.getNombre());
                                                        carreraDTO.setDuracion(carrera.getDuracion());
                                                        return carreraDTO;
                                                    }).toList();

                                            sedeDTO.setCarreras(carreraDTOs);

                                            // Solo agregar la sede si tiene carreras
                                            if (!carreraDTOs.isEmpty()) {
                                                return sedeDTO;
                                            } else {
                                                return null; // Filtrar sedes sin carreras
                                            }
                                        })
                                        .filter(Objects::nonNull) // Eliminar las sedes nulas
                                        .toList();

                                ciudadDTO.setSedes(sedeDTOs);
                                return ciudadDTO;
                            }).toList();

                    universidadDTO.setCiudades(ciudadDTOs);
                    return universidadDTO;
                }).toList();

        return buildResponse("Ok", universidadDTOs, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<List<UniversidadDTO>>> obtenerUniversidades() {
        List<Universidad> universidades = universidadRepository.findAllByEstado("");

        // Convertimos las entidades a DTO sin ciudades
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
                universidad.getTipoUniversidad()
        );
    }

}
