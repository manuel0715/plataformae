package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Sede;
import com.plataformae.ws.dto.SedeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISedeRepository extends JpaRepository<Sede, Integer> {
    List<Sede> findByCiudadIdAndUniversidadId(Long ciudadId, Long universidadId);

    // Filtrar solo por ciudad
    List<Sede> findByCiudadId(Long ciudadId);

    // Filtrar solo por universidad
    List<Sede> findByUniversidadId(Long universidadId);


}
