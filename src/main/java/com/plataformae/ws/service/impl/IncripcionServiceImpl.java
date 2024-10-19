package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.db.repository.*;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.InscripcionRequestDTO;
import com.plataformae.ws.dto.InscripcionResponseDTO;
import com.plataformae.ws.service.IInscripcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class IncripcionServiceImpl implements IInscripcionService {

    private final IUniversidadRepository universidadRepository;
    private final ICiudadRepository ciudadRepository;
    private final ISedeRepository sedeRepository;
    private final ICarreraRepository carreraRepository;
    private final IInscripcionesRepository preInscripcionesRepository;

    public IncripcionServiceImpl(IUniversidadRepository universidadRepository, ICiudadRepository ciudadRepository, ISedeRepository sedeRepository, ICarreraRepository carreraRepository,IInscripcionesRepository preInscripcionesRepository) {
        this.universidadRepository = universidadRepository;
        this.ciudadRepository = ciudadRepository;
        this.sedeRepository = sedeRepository;
        this.carreraRepository = carreraRepository;
        this.preInscripcionesRepository = preInscripcionesRepository;
    }


    @Override
    public ResponseEntity<ApiResponseDTO<InscripcionResponseDTO>> guardarInscripcion(InscripcionRequestDTO request) {

        boolean exist = preInscripcionesRepository.existsByUsuarioAndUniversidadAndCiudadAndSedeAndCarrera(request.getUsuarioId(),
                request.getUniversidadId(), request.getCiudadId(), request.getSedeId(), request.getCarreraId());

        if (exist){
            return buildResponse(
                    "Usuario ya se encuentra registrado para esta carrera",
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }

        Inscripciones incripciones = new Inscripciones();
        InscripcionResponseDTO inscripcionResponseDTO = new InscripcionResponseDTO();

        incripciones.setUsuarioId(request.getUsuarioId());

        Universidad universidad = universidadRepository.findById(request.getUniversidadId())
                .orElseThrow(() -> new RuntimeException("Universidad no encontrada"));
        incripciones.setUniversidad(universidad);


        Ciudad ciudad = ciudadRepository.findById(request.getCiudadId())
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        incripciones.setCiudad(ciudad);

        Sede sede = sedeRepository.findById(request.getSedeId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        incripciones.setSede(sede);

        Carrera carrera = carreraRepository.findById(request.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        incripciones.setCarrera(carrera);


        inscripcionResponseDTO.setUsuario(request.getUsuarioId());
        inscripcionResponseDTO.setUniversidad(incripciones.getUniversidad().getNombre());
        inscripcionResponseDTO.setCarrera(incripciones.getCarrera().getNombre());

        preInscripcionesRepository.save(incripciones);

        return buildResponse("Ok", inscripcionResponseDTO, HttpStatus.OK);

    }
}
