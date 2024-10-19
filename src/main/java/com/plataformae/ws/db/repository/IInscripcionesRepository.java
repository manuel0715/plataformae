package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Inscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IInscripcionesRepository extends JpaRepository<Inscripciones,Integer> {

    @Query("SELECT COUNT(i) > 0 FROM Inscripciones i WHERE i.usuarioId = :usuarioId " +
            "AND i.universidad.id = :universidadId " +
            "AND i.ciudad.id = :ciudadId " +
            "AND i.sede.id = :sedeId " +
            "AND i.carrera.id = :carreraId")
    boolean existsByUsuarioAndUniversidadAndCiudadAndSedeAndCarrera(
            @Param("usuarioId") String usuarioId,
            @Param("universidadId") Integer universidadId,
            @Param("ciudadId") Integer ciudadId,
            @Param("sedeId") Integer sedeId,
            @Param("carreraId") Integer carreraId);
}
