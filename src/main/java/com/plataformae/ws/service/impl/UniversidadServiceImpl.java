package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.db.repository.IUniversidadRepository;
import com.plataformae.ws.domain.Base64Service;
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

    private final Base64Service base64Service;

    public UniversidadServiceImpl(IUniversidadRepository universidadRepository, Base64Service base64Service) {
        this.universidadRepository = universidadRepository;
        this.base64Service = base64Service;
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>> buscarUniversidades(String universidad, String municipio, String carr, String sed) {
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
                Join<Universidad, RelUniversidadMunicipio> relJoin = root.join("relUniversidadMunicipio");
                Join<RelUniversidadMunicipio, Sede> sedeJoin = relJoin.join("sede");
                return criteriaBuilder.equal(sedeJoin.get("id"), sedeId);
            });
        }

        // Filtro por ID de Municipio
        if (municipio != null && !municipio.isEmpty()) {
            Long municipioId = Long.valueOf(municipio);
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Universidad, RelUniversidadMunicipio> relJoin = root.join("relUniversidadMunicipio");
                Join<RelUniversidadMunicipio, Municipio> municipioJoin = relJoin.join("municipio");
                return criteriaBuilder.equal(municipioJoin.get("id"), municipioId);
            });
        }

        // Filtro por carrera
        if (carr != null && !carr.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Universidad, RelUniversidadMunicipio> relJoin = root.join("relUniversidadMunicipio");
                Join<RelUniversidadMunicipio, Sede> sedeJoin = relJoin.join("sede");
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
                    universidadDTO.setLogoBase64(base64Service.convertirBase64(uni.getLogo()));
                    universidadDTO.setTerminosCondiciones(base64Service.convertirBase64(uni.getTerminosCondiciones()));


                    // Mapear municipio
                    List<MunicipioDTO> municipioDTOS = uni.getRelUniversidadMunicipio().stream()
                            .map(rel -> {
                                MunicipioDTO municipioDTO = new MunicipioDTO();
                                municipioDTO.setId(rel.getMunicipio().getId());
                                municipioDTO.setEstado(rel.getMunicipio().getEstado());
                                municipioDTO.setNombre(rel.getMunicipio().getNombre());
                                municipioDTO.setCodigoPostal(rel.getMunicipio().getCodigoPostal());
                                municipioDTO.setAbreviatura(rel.getMunicipio().getAbreviatura());

                                // Mapear sedes
                                List<SedeDTO> sedeDTOs = rel.getSede().stream()
                                        .map(sede -> {
                                            SedeDTO sedeDTO = new SedeDTO();
                                            sedeDTO.setId(sede.getId());
                                            sedeDTO.setEstado(sede.getEstado());
                                            sedeDTO.setNombre(sede.getNombre());
                                            sedeDTO.setDireccion(sede.getDireccion());

                                            // Mapear carreras con filtro
                                            List<CarreraDTO> carreraDTOs = sede.getRelSedeCarrera().stream()
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

                                municipioDTO.setSedes(sedeDTOs);
                                return municipioDTO;
                            }).toList();

                    universidadDTO.setMunicipios(municipioDTOS);
                    return universidadDTO;
                }).toList();

        return buildResponse("Ok", universidadDTOs, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponseDTO<List<UniversidadDTO>>> obtenerUniversidades() {
        List<Universidad> universidades = universidadRepository.findAllByEstado("");

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
                base64Service.convertirBase64(universidad.getLogo()),
                base64Service.convertirBase64(universidad.getTerminosCondiciones()));
    }

}
