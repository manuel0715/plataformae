package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.Carrera;
import com.plataformae.ws.db.repository.ICarreraRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.service.ICarreraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class CarreraServiceImpl implements ICarreraService {
    private final ICarreraRepository carreraRepository;

    public CarreraServiceImpl(ICarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Override
    public ResponseEntity<ApiResponseDTO<List<Carrera>>> obtenerCarreras() {

        List<Carrera> list = carreraRepository.findAllByOrderByNombreAsc();

        return buildResponse("Ok", list, HttpStatus.OK);
    }
}
