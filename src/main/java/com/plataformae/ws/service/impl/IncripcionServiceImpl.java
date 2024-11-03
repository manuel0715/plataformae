package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.db.repository.*;
import com.plataformae.ws.dto.*;
import com.plataformae.ws.service.IInscripcionService;
import com.plataformae.ws.util.AuthUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class IncripcionServiceImpl implements IInscripcionService {

    private final AuthUtil authUtil;
    private final IUniversidadRepository universidadRepository;
    private final IMunicipioRepository municipioRepository;
    private final ISedeRepository sedeRepository;
    private final ICarreraRepository carreraRepository;
    private final IInscripcionesRepository preInscripcionesRepository;
    private final InscripcionRepository inscripcionRepository;
    private final IUsuariosRepository iUsuariosRepository;
    private final ITrazabilidadGestionInscripcionesRepository trazabilidadGestionInscripcionesRepository;
    private final IEstadoProcesoRepository estadoProcesoRepository;
    private final IEstadoContactoRepository estadoContactoRepository;

    public IncripcionServiceImpl(AuthUtil authUtil, IUniversidadRepository universidadRepository, IMunicipioRepository municipioRepository, ISedeRepository sedeRepository, ICarreraRepository carreraRepository, IInscripcionesRepository preInscripcionesRepository, InscripcionRepository inscripcionRepository, IUsuariosRepository iUsuariosRepository, ITrazabilidadGestionInscripcionesRepository trazabilidadGestionInscripcionesRepository, IEstadoProcesoRepository estadoProcesoRepository, IEstadoContactoRepository estadoContactoRepository) {
        this.authUtil = authUtil;
        this.universidadRepository = universidadRepository;
        this.municipioRepository = municipioRepository;
        this.sedeRepository = sedeRepository;
        this.carreraRepository = carreraRepository;
        this.preInscripcionesRepository = preInscripcionesRepository;
        this.inscripcionRepository = inscripcionRepository;
        this.iUsuariosRepository = iUsuariosRepository;
        this.trazabilidadGestionInscripcionesRepository = trazabilidadGestionInscripcionesRepository;
        this.estadoProcesoRepository = estadoProcesoRepository;
        this.estadoContactoRepository = estadoContactoRepository;
    }


    @Override
    public ResponseEntity<ApiResponseDTO<InscripcionResponseDTO>> guardarInscripcion(InscripcionRequestDTO request) {

        boolean exist = preInscripcionesRepository.existsByUsuarioAndUniversidadAndMunicipioAndSedeAndCarrera(request.getUsuarioId(),
                request.getUniversidadId(), request.getMunicipioId(), request.getSedeId(), request.getCarreraId());

        if (exist){
            return buildResponse(
                    "Usuario ya se encuentra registrado para esta carrera",
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }

        Inscripciones incripciones = new Inscripciones();
        InscripcionResponseDTO inscripcionResponseDTO = new InscripcionResponseDTO();

        Usuarios usuarios = iUsuariosRepository.findByUsername(request.getUsuarioId());
        incripciones.setUsuarios(usuarios);

        Universidad universidad = universidadRepository.findById(request.getUniversidadId())
                .orElseThrow(() -> new RuntimeException("Universidad no encontrada"));
        incripciones.setUniversidad(universidad);


        Municipio municipio = municipioRepository.findById(request.getMunicipioId())
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));
        incripciones.setMunicipio(municipio);

        Sede sede = sedeRepository.findById(request.getSedeId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        incripciones.setSede(sede);

        Carrera carrera = carreraRepository.findById(request.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        incripciones.setCarrera(carrera);

        incripciones.setAnioGraduacion(request.getAnioGraduacion());
        incripciones.setSemestreInicioEstudio(request.getSemestreInicioEstudio());


        inscripcionResponseDTO.setUsuario(request.getUsuarioId());
        inscripcionResponseDTO.setUniversidad(incripciones.getUniversidad().getNombre());
        inscripcionResponseDTO.setCarrera(incripciones.getCarrera().getNombre());


        preInscripcionesRepository.save(incripciones);

        return buildResponse("Ok", inscripcionResponseDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiResponseDTO<List<CargarInscripcionResponseDTO>>> cargarMisInscripciones() {


        List<CargarInscripcionResponseDTO> list = preInscripcionesRepository.findInscripcionesByUsuarioIdAndFechaCreacion(authUtil.getAuthenticatedUser());
        return buildResponse("Ok",list,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponseDTO<List<CargarInscripcionResponseDTO>>> cargarInscripciones(LocalDate fechaInicio, LocalDate fechaFin, List<Integer> estadoProcesoIds, List<Integer> estadoContactoIds) {
        List<CargarInscripcionResponseDTO> list = inscripcionRepository.findInscripciones(fechaInicio, fechaFin, estadoProcesoIds, estadoContactoIds);
        return buildResponse("Ok",list,HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponseDTO<String>> guardarGestionInscripcion(GuardarGestionIncripcionDTO request) {

        TrazabilidadGestionInscripciones gestionInscripciones = new TrazabilidadGestionInscripciones();
        Inscripciones inscripciones = preInscripcionesRepository.findById(request.getInscripcionId()).orElse(null);

        if (inscripciones == null)   {
            return  buildResponse("id inscripción no existe ",null,HttpStatus.BAD_REQUEST);
        }
        EstadoProceso estadoProceso = estadoProcesoRepository.findById(request.getEstadoProcesoId()).orElse(null);

        if (estadoProceso == null)   {
            return  buildResponse("Id estado proceso no existe ",null,HttpStatus.BAD_REQUEST);
        }

        EstadoContacto estadoContacto= estadoContactoRepository.findById(request.getEstadoContactoId()).orElse(null);

        if (estadoContacto == null)   {
            return  buildResponse("Id estado contacto no existe ",null,HttpStatus.BAD_REQUEST);
        }

        Usuarios usuario =iUsuariosRepository.findByUsername(authUtil.getAuthenticatedUser());

        if (usuario == null)   {
            return  buildResponse("Usuario estado contacto no existe ",null,HttpStatus.BAD_REQUEST);
        }

        gestionInscripciones.setInscripciones(inscripciones);
        gestionInscripciones.setEstadoContacto(estadoContacto);
        gestionInscripciones.setEstadoProceso(estadoProceso);
        gestionInscripciones.setFechaProximaLlamada(request.getFechaProximaLlamada().atStartOfDay());
        gestionInscripciones.setComentarioGestion(request.getComentarioGestion());
        gestionInscripciones.setUsuarioGestion(usuario);
        gestionInscripciones.setFechaGestion(LocalDateTime.now());

        trazabilidadGestionInscripcionesRepository.save(gestionInscripciones);
        preInscripcionesRepository.actualizarGestionInscripcion(
                gestionInscripciones.getInscripciones().getId(),
                gestionInscripciones.getFechaProximaLlamada(),
                gestionInscripciones.getFechaGestion(),
                gestionInscripciones.getComentarioGestion(),
                gestionInscripciones.getUsuarioGestion().getUsername(),
                gestionInscripciones.getEstadoProceso(),
                gestionInscripciones.getEstadoContacto());


        return  buildResponse("Gestión guardada con éxito",null,HttpStatus.OK);
    }
}
