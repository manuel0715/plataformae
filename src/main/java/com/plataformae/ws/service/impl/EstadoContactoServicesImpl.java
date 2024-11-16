package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.EstadoContacto;
import com.plataformae.ws.db.repository.jpa.IEstadoContactoRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.service.IEstadoContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class EstadoContactoServicesImpl implements IEstadoContactoService {

    @Autowired
    private IEstadoContactoRepository iEstadoContactoRepository;

    @Override
    public ResponseEntity<ApiResponseDTO<List<EstadoContacto>>> obtenerEstadosContacto() {

        List<EstadoContacto> list = iEstadoContactoRepository.findAll();
        return buildResponse("Ok", list, HttpStatus.OK);
    }
}
