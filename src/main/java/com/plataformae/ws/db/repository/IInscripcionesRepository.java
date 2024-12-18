package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.EstadoContacto;
import com.plataformae.ws.db.entity.EstadoProceso;
import com.plataformae.ws.db.entity.Inscripciones;
import com.plataformae.ws.dto.CargarInscripcionResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface IInscripcionesRepository extends JpaRepository<Inscripciones,Integer> {

    @Query("SELECT COUNT(i) > 0 FROM Inscripciones i WHERE i.usuarios.username = :usuarioId " +
            "AND i.universidad.id = :universidadId " +
            "AND i.municipio.id = :municipioId " +
            "AND i.carrera.id = :carreraId")
    boolean existsByUsuarioAndUniversidadAndMunicipioAndCarrera(
            @Param("usuarioId") String usuarioId,
            @Param("universidadId") Integer universidadId,
            @Param("municipioId") Integer municipioId,
            @Param("carreraId") Integer carreraId);



    @Query("SELECT new com.plataformae.ws.dto.CargarInscripcionResponseDTO(" +
            "i.id, " + // id de la inscripción
            "u2.tipoIdentificacion, " + // tipo de identificación del usuario
            "u2.nombreCompleto, " + // nombres del usuario
            "u2.celular, " + // celular del usuario
            "u2.email, " + // email del usuario
            "u.nombre, " + // nombre de la universidad
            "c.nombre, " + // nombre de la municipio
            "c2.nombre, " + // nombre de la carrera
            "ep.nombre, " + // nombre del estado de proceso
            "ec.nombre, " + // nombre del estado de contacto
            "i.fechaCreacion,i.anioGraduacion,i.semestreInicioEstudio) " + // fecha de creación
            "FROM Inscripciones i " +
            "JOIN i.usuarios u2 " + // unión con la tabla de usuarios
            "JOIN i.universidad u " + // unión con la tabla de universidad
            "JOIN i.municipio c " + // unión con la tabla de municipio
            "JOIN i.carrera c2 " + // unión con la tabla de carrera
            "JOIN i.estadoProceso ep " + // unión con la tabla de estado de proceso
            "JOIN i.estadoContacto ec " + // unión con la tabla de estado de contacto
            "WHERE i.usuarios.username = :usuarioId ")
    List<CargarInscripcionResponseDTO> findInscripcionesByUsuarioIdAndFechaCreacion(
            @Param("usuarioId") String usuarioId);



    @Modifying
    @Transactional
    @Query("UPDATE Inscripciones i SET i.usuarioUltimaGestion=:usuarioUltimaGestion," +
            " i.ultimoComentarioGestion=:ultimoComentarioGestion," +
            " i.fechaUltimaGestion=:fechaUltimaGestion," +
            " i.fechaProximaLlamada=:fechaProximaLlamada," +
            " i.estadoProceso = :estadoProceso," +
            " i.estadoContacto = :estadoContacto " +
            "WHERE i.id = :id")
    void actualizarGestionInscripcion(@Param("id") Integer id,
                          @Param("fechaProximaLlamada") LocalDateTime fechaProximaLlamada,
                          @Param("fechaUltimaGestion") LocalDateTime fechaUltimaGestion,
                          @Param("ultimoComentarioGestion") String ultimoComentarioGestion,
                          @Param("usuarioUltimaGestion") String usuarioUltimaGestion,
                          @Param("estadoProceso") EstadoProceso estadoProceso,
                          @Param("estadoContacto") EstadoContacto estadoContacto);

}
