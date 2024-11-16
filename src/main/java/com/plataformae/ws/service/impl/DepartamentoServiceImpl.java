package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.Departamento;
import com.plataformae.ws.db.repository.jpa.IDepartamentoRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.service.IDepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

    private final IDepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(IDepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }


    @Override
    public ResponseEntity<ApiResponseDTO<List<Departamento>>> obtenerDepartamentos() {

        List<Departamento> list = departamentoRepository.findAll();

        return buildResponse("Ok", list, HttpStatus.OK);
    }
}
