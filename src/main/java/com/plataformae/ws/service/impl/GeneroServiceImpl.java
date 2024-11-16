package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.Genero;
import com.plataformae.ws.db.repository.jpa.IGeneroRepository;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.service.IGeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.plataformae.ws.util.Utils.buildResponse;

@Service
public class GeneroServiceImpl implements IGeneroService {

    private final IGeneroRepository generoRepository;

    public GeneroServiceImpl(IGeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public ResponseEntity<ApiResponseDTO<List<Genero>>> obtenerGenero() {

        List<Genero> list = generoRepository.findAll();

        return buildResponse("Ok", list, HttpStatus.OK);
    }
}
