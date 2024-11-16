package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.Modalidad;
import com.plataformae.ws.db.repository.IModalidadRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.service.IModalidadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class ModalidadServiceImpl implements IModalidadService {

    private final IModalidadRepository modalidadRepository;

    public ModalidadServiceImpl(IModalidadRepository modalidadRepository) {
        this.modalidadRepository = modalidadRepository;
    }

    @Override
    public ResponseEntity<ApiResponseDTO<List<Modalidad>>> obtenerModalidad() {
        List<Modalidad> list = modalidadRepository.findAll();

        return buildResponse("Ok", list, HttpStatus.OK);
    }
}
