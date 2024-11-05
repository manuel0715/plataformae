package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.TrazabilidadGestionInscripciones;
import com.plataformae.ws.dto.TrazabilidadResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITrazabilidadGestionInscripcionesRepository extends JpaRepository<TrazabilidadGestionInscripciones,Integer> {

    @Query("select new com.plataformae.ws.dto.TrazabilidadResponseDTO(" +
            "ti.id," +
            "ti.inscripciones.id," +
            "ti.estadoProceso.nombre," +
            "ti.estadoContacto.nombre," +
            "ti.fechaGestion," +
            "ti.fechaProximaLlamada," +
            "ti.comentarioGestion," +
            "ti.usuarioGestion.username) " +
            "from TrazabilidadGestionInscripciones ti " +
            "where ti.inscripciones.id=:inscripcionId")
    List<TrazabilidadResponseDTO> cargarTrazabilidad(@Param("inscripcionId") int inscripcionId);
}
