package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.Carrera;
import com.plataformae.ws.db.entity.Ciudad;
import com.plataformae.ws.db.entity.Sede;
import com.plataformae.ws.db.entity.Universidad;
import com.plataformae.ws.db.repository.IUniversidadRepository;
import com.plataformae.ws.dto.*;
import com.plataformae.ws.service.IUniversidadService;
import jakarta.persistence.criteria.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            Long sedeId = Long.valueOf(sed); // Convierte la cadena a Long
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Universidad, Sede> sedeJoin = root.join("sede");
                return criteriaBuilder.equal(sedeJoin.get("id"), sedeId);
            });
        }

        // Filtro por ID de ciudad
        if (ciudad != null && !ciudad.isEmpty()) {
            Long ciudadId = Long.valueOf(ciudad);
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Universidad, Sede> sedeJoin = root.join("sede");
                Join<Sede, Ciudad> ciudadJoin = sedeJoin.join("ciudad");
                return criteriaBuilder.equal(ciudadJoin.get("id"), ciudadId);
            });
        }

        // Filtro por carrera
        if (carr != null && !carr.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Universidad, Sede> sedeJoin = root.join("sede");
                Join<Sede, Carrera> carreraJoin = sedeJoin.join("carrera");
                return criteriaBuilder.like(criteriaBuilder.upper(carreraJoin.get("nombre")), "%" + carr.toUpperCase() + "%");
            });
        }

        List<Universidad> universidades = universidadRepository.findAll(spec);

        // Mapear a DTOs
        List<UniversidadDTO> universidadDTOs = universidades.stream()
                .map(uni -> {
                    UniversidadDTO universidadDTO = new UniversidadDTO();
                    universidadDTO.setId(uni.getId());
                    universidadDTO.setEstado(uni.getEstado());
                    universidadDTO.setNombre(uni.getNombre());
                    universidadDTO.setNit(uni.getNit());
                    universidadDTO.setTipoUniversidad(uni.getTipoUniversidad());

                    // Mapear sedes
                    List<SedeDTO> sedeDTOs = uni.getSedes().stream()
                            .map(sede -> {
                                SedeDTO sedeDTO = new SedeDTO();
                                sedeDTO.setId(sede.getId());
                                sedeDTO.setEstado(sede.getEstado());
                                sedeDTO.setNombre(sede.getNombre());
                                sedeDTO.setDireccion(sede.getDireccion());

                                // Mapear ciudad
                                CiudadDTO ciudadDTO = new CiudadDTO();
                                ciudadDTO.setId(sede.getCiudad().getId());
                                ciudadDTO.setEstado(sede.getCiudad().getEstado());
                                ciudadDTO.setNombre(sede.getCiudad().getNombre());
                                ciudadDTO.setCodigoPostal(sede.getCiudad().getCodigoPostal());
                                ciudadDTO.setAbreviatura(sede.getCiudad().getAbreviatura());

                                sedeDTO.setCiudad(ciudadDTO);

                                // Mapear carreras
                                List<CarreraDTO> carreraDTOs = sede.getCarreras().stream()
                                        .map(carrera -> {
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

                    universidadDTO.setSedes(sedeDTOs);
                    return universidadDTO;
                }).toList();

        return buildResponse("Ok", universidadDTOs, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<List<UniversidadDTO>>> obtenerUniversidades() {
        List<Universidad> universidades = universidadRepository.findAllByEstado("");

        List<UniversidadDTO> universidadDTOs = universidades.stream()
                .map(u -> {
                    List<SedeDTO> sedeDTOs = u.getSede().stream()
                            .map(s -> new SedeDTO(s.getId(),
                                    s.getNombre(),
                                    s.getDireccion(),
                                    s.getEstado(),
                                    convertirACiudadDTO(s.getCiudad()),
                                    s.getCarreras().stream().map(this::convertirACarreraDTO).toList()
                            )).toList();

                    return new UniversidadDTO(
                            u.getId(),
                            u.getEstado(),
                            u.getNombre(),
                            u.getNit(),
                            u.getTipoUniversidad(),
                            sedeDTOs
                    );
                }).toList();

        return buildResponse("Ok", universidadDTOs, HttpStatus.OK);

    }

    private CiudadDTO convertirACiudadDTO(Ciudad ciudad) {
        CiudadDTO ciudadDTO = new CiudadDTO();
        ciudadDTO.setId(ciudad.getId());
        ciudadDTO.setNombre(ciudad.getNombre());
        ciudadDTO.setEstado(ciudadDTO.getEstado());
        return ciudadDTO;
    }

    private CarreraDTO convertirACarreraDTO(Carrera carrera) {
        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setId(carrera.getId());
        carreraDTO.setNombre(carrera.getNombre());
        carreraDTO.setDuracion(carrera.getDuracion());
        carreraDTO.setEstado(carrera.getEstado());
        carreraDTO.getTipo(carrera.getTipo());
        return carreraDTO;
    }

}
