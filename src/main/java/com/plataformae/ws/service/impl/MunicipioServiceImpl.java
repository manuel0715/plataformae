package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.Departamento;
import com.plataformae.ws.db.entity.Municipio;
import com.plataformae.ws.db.repository.jpa.IDepartamentoRepository;
import com.plataformae.ws.db.repository.jpa.IMunicipioRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.service.IMunicipioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class MunicipioServiceImpl implements IMunicipioService {

    private final IMunicipioRepository municipioRepository;
    private final IDepartamentoRepository departamentoRepository;

    public MunicipioServiceImpl(IMunicipioRepository municipioRepository, IDepartamentoRepository departamentoRepository) {
        this.municipioRepository = municipioRepository;
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public ResponseEntity<ApiResponseDTO<List<Municipio>>> obtenerMunicipio(Integer departamentoId) {


        Optional<Departamento> departamento = departamentoRepository.findById(departamentoId);

        List<Municipio> list = departamento.isPresent()
                ? municipioRepository.findByDepartamento(departamento)
                : Collections.emptyList();

        return buildResponse("Ok", list, HttpStatus.OK);
    }
}
