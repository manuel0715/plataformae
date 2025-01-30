package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.db.repository.*;
import com.plataformae.ws.dto.*;
import com.plataformae.ws.service.IUniversidadService;
import com.plataformae.ws.util.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.plataformae.ws.util.Utils.buildResponse;
import static com.plataformae.ws.util.Utils.convertirBase64;

@Service
public class UniversidadServiceImpl implements IUniversidadService {


    private final AuthUtil authUtil;
    private final IUniversidadRepository universidadRepository;
    private final BuscarCarreraRepository buscarCarreraRepository;
    private final IMunicipioRepository municipioRepository;
    private final ICarreraRepository carreraJpaRepository;
    private final IModalidadRepository modalidadRepository;
    private final IRelCarreraUniversidadMunicipioRepository relCarreraUniversidadMunicipioRepository;



    public UniversidadServiceImpl(AuthUtil authUtil, IUniversidadRepository universidadRepository, BuscarCarreraRepository buscarCarreraRepository, IMunicipioRepository municipioRepository, ICarreraRepository carreraJpaRepository, IModalidadRepository modalidadRepository, IRelCarreraUniversidadMunicipioRepository relCarreraUniversidadMunicipioRepository) {
        this.authUtil = authUtil;
        this.municipioRepository = municipioRepository;
        this.carreraJpaRepository = carreraJpaRepository;
        this.universidadRepository = universidadRepository;
        this.buscarCarreraRepository = buscarCarreraRepository;
        this.modalidadRepository = modalidadRepository;
        this.relCarreraUniversidadMunicipioRepository = relCarreraUniversidadMunicipioRepository;
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
                universidad.getUsuarioUltimaModificacion(),
                convertirBase64(universidad.getLogo()),
                convertirBase64(universidad.getTerminosCondiciones()),
                universidad.getDireccion(),
                universidad.getTelefono(),
                universidad.getRelCarreraUniversidadMunicipio());
    }

    public ResponseEntity<ApiResponseDTO<List<CarreraUniversidadResponseDTO>>> buscarCarreras(String terminoBusqueda,Integer universidad, Integer modalidad,Integer  municipio) {
        List<CarreraUniversidadResponseDTO> list = buscarCarreraRepository.buscarCarreras(terminoBusqueda,universidad,modalidad,municipio);
        return buildResponse("Ok", list, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseDTO<String>> upsertUniversidad(GestionUniversidadDTO universidadDto) {
        Universidad universidad;

        if (universidadDto.getId() != null) {

            universidad = universidadRepository.findById(universidadDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Universidad no encontrada"));

            Optional<Universidad> existingUniversidad = universidadRepository.validateNit(universidadDto.getNit());
            if (existingUniversidad.isPresent() && !existingUniversidad.get().getId().equals(universidad.getId())) {
                return buildResponse("El nit ingresado ya se encuentra registrado por favor validar", null, HttpStatus.BAD_REQUEST);
            }

            universidad.setEstado(universidadDto.getEstado().toUpperCase());
            universidad.setNombre(universidadDto.getNombre().toUpperCase());
            universidad.setNit(universidadDto.getNit());
            universidad.setTipoUniversidad(universidadDto.getTipoUniversidad().toUpperCase());
            universidad.setUsuarioUltimaModificacion(authUtil.getAuthenticatedUser());
            universidad.setFechaUltimaModificacion(LocalDateTime.now());
            universidad.setLogo(universidadDto.getLogoBase64());
            universidad.setTerminosCondiciones(universidadDto.getTerminosCondiciones());
            universidad.setDireccion(universidadDto.getDireccion().toUpperCase());
            universidad.setTelefono(universidadDto.getTelefono());
        } else {
            if (universidadRepository.existsByNit(universidadDto.getNit())){
                return buildResponse("El nit ingresado ya se encuentra registrado por favor validar", null, HttpStatus.BAD_REQUEST);
            }

            universidad = new Universidad();
            universidad.setEstado(universidadDto.getEstado().toUpperCase());
            universidad.setNombre(universidadDto.getNombre().toUpperCase());
            universidad.setNit(universidadDto.getNit());
            universidad.setTipoUniversidad(universidadDto.getTipoUniversidad().toUpperCase());
            universidad.setUsuarioCreador(authUtil.getAuthenticatedUser());
            universidad.setFechaCreacion(LocalDateTime.now());
            universidad.setLogo(universidadDto.getLogoBase64());
            universidad.setTerminosCondiciones(universidadDto.getTerminosCondiciones());
            universidad.setDireccion(universidadDto.getDireccion().toUpperCase());
            universidad.setTelefono(universidadDto.getTelefono());
        }

            universidad = universidadRepository.save(universidad);

        // Procesar carreras
        if (universidadDto.getCarreras() != null && !universidadDto.getCarreras().isEmpty()) {

            for (RelCarreraUniversidadMunicipioDTO carreraDto : universidadDto.getCarreras()) {

                Municipio municipio = municipioRepository.findById(carreraDto.getMunicipioId())
                        .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));

                Carrera carrera = carreraJpaRepository.findById(carreraDto.getCarreraId())
                        .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

                Modalidad modalidad =modalidadRepository.findById(carreraDto.getModalidadId())
                        .orElseThrow(() -> new RuntimeException("Modalidad no encontrada"));


                Optional<RelCarreraUniversidadMunicipio> existingRel = relCarreraUniversidadMunicipioRepository
                        .findByUniversidadAndMunicipioAndCarreraAndModalidad(universidad, municipio, carrera, modalidad);

                if (existingRel.isPresent()) {
                    // Si ya existe, actualizamos el registro o lo ignoramos
                    RelCarreraUniversidadMunicipio relToUpdate = existingRel.get();
                    relToUpdate.setUniversidad(universidad);
                    relToUpdate.setNombreCarrera(carrera.getNombre().toUpperCase());
                    relToUpdate.setDescripcion(carreraDto.getDescripcion().toUpperCase());
                    relToUpdate.setPensum(carreraDto.getPensum());
                    relToUpdate.setCarrera(carrera);
                    relToUpdate.setNombreCarrera(carrera.getNombre().toUpperCase());
                    relToUpdate.setValorSemestre(carreraDto.getValorSemestre());
                    relToUpdate.setTituloOtorgado(carreraDto.getTituloOtorgado().toUpperCase());
                    relToUpdate.setDuracion(carreraDto.getDuracion());

                    relCarreraUniversidadMunicipioRepository.save(relToUpdate);
                } else {

                    RelCarreraUniversidadMunicipio rel = new RelCarreraUniversidadMunicipio();
                    rel.setUniversidad(universidad);
                    rel.setNombreUniversidad(universidad.getNombre().toUpperCase());
                    rel.setMunicipio(municipio);
                    rel.setCarrera(carrera);
                    rel.setNombreCarrera(carrera.getNombre().toUpperCase());
                    rel.setModalidad(modalidad);
                    rel.setDescripcion(carreraDto.getDescripcion().toUpperCase());
                    rel.setPensum(carreraDto.getPensum());
                    rel.setValorSemestre(carreraDto.getValorSemestre());
                    rel.setTituloOtorgado(carreraDto.getTituloOtorgado().toUpperCase());
                    rel.setDuracion(carreraDto.getDuracion());
                    relCarreraUniversidadMunicipioRepository.save(rel);
                }

            }
        }

        return buildResponse("Actualización Éxitosa", null, HttpStatus.OK);
    }

}
