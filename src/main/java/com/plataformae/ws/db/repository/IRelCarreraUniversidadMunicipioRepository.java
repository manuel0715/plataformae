package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.*;
import com.plataformae.ws.dto.RelCarreraUniversidadMunicipioDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRelCarreraUniversidadMunicipioRepository extends JpaRepository<RelCarreraUniversidadMunicipio, Long> {


    Optional<RelCarreraUniversidadMunicipio> findByUniversidadAndMunicipioAndCarreraAndModalidad(Universidad universidad, Municipio municipio, Carrera carrera, Modalidad modalidad);

    List<RelCarreraUniversidadMunicipioDTO> findByUniversidad(Universidad universidad);
}
