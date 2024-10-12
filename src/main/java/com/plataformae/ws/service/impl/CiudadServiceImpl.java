package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.Ciudad;
import com.plataformae.ws.db.repository.ICiudadRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.service.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class CiudadServiceImpl implements ICiudadService{

    private final ICiudadRepository iCiudadRepository;

    @Autowired
    public CiudadServiceImpl(ICiudadRepository iCiudadRepository) {
        this.iCiudadRepository = iCiudadRepository;
    }

    @Override
    public ResponseEntity<ApiResponseDTO<List<Ciudad>>> obtenerTodasLasCiudades() {

        List<Ciudad>  ciudades =iCiudadRepository.findAll();

        return buildResponse("Ok", ciudades, HttpStatus.OK);
    }
}
