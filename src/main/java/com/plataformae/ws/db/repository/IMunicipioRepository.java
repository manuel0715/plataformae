package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Departamento;
import com.plataformae.ws.db.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMunicipioRepository extends JpaRepository <Municipio, Integer> {

    List<Municipio> findByDepartamento(Optional<Departamento> departamento);
}
