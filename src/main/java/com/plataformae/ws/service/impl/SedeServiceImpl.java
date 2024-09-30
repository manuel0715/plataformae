package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.Carrera;
import com.plataformae.ws.db.entity.Ciudad;
import com.plataformae.ws.db.entity.Sede;
import com.plataformae.ws.db.repository.ISedeRepository;
import com.plataformae.ws.dto.ApiResponse;
import com.plataformae.ws.dto.CarreraDTO;
import com.plataformae.ws.dto.CiudadDTO;
import com.plataformae.ws.dto.SedeDTO;
import com.plataformae.ws.service.ISedeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class SedeServiceImpl implements ISedeService {

    private final ISedeRepository iSedeRepository;

    public SedeServiceImpl(ISedeRepository iSedeRepository) {
        this.iSedeRepository = iSedeRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<ApiResponse<List<SedeDTO>>> obtenerSedes(Long universidadId, Long ciudadId) {
        List<Sede> sedesEntities = List.of();

        if (ciudadId != null && universidadId != null) {
            sedesEntities = iSedeRepository.findByCiudadIdAndUniversidadId(ciudadId, universidadId);
        } else if (universidadId != null) {
            sedesEntities = iSedeRepository.findByUniversidadId(universidadId);
        }

        List<SedeDTO> sedeDTOs = sedesEntities.stream()
                .map(sede -> new SedeDTO(
                        sede.getId(),
                        sede.getEstado(),
                        sede.getNombre(),
                        sede.getDireccion(),
                        convertirACiudadDTO(sede.getCiudad()),
                        sede.getCarreras().stream().map(this::convertirACarreraDTO).toList()
                )).toList();

        return buildResponse("Ok", sedeDTOs, HttpStatus.OK);
    }

    private CiudadDTO convertirACiudadDTO(Ciudad ciudad) {
        CiudadDTO ciudadDTO = new CiudadDTO();
        ciudadDTO.setId(ciudad.getId());
        ciudadDTO.setNombre(ciudad.getNombre());
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
