package com.plataformae.ws.db.repository;

import com.plataformae.ws.dto.CargarInscripcionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class InscripcionRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<CargarInscripcionResponseDTO> findInscripciones(LocalDate fechaInicio, LocalDate fechaFin,
                                                                List<Integer> estadoProcesoIds, List<Integer> estadoContactoIds) {
        String sql = "SELECT i.id AS inscripcion_id, \n" +
                " u2.tipo_identificacion,\n" +
                " u2.nombre_completo, \n" +
                " u2.celular, \n" +
                " u2.email, \n" +
                " u.nombre AS universidad,\n" +
                " c.nombre AS municipio,\n" +
                " s.nombre AS sede,\n" +
                " c2.nombre AS carrera, \n" +
                " ep.nombre AS estado_proceso,\n" +
                " ec.nombre AS estado_contacto,\n" +
                " i.fecha_creacion,i.anio_graduacion,i.semestre_inicio_estudio \n" +
                " FROM gestion_academica.inscripciones i \n" +
                " INNER JOIN administrativo.usuarios u2 ON u2.usuario = i.usuario_id \n" +
                " INNER JOIN configuracion.universidad u ON u.id = i.universidad_id \n" +
                " INNER JOIN configuracion.municipio c ON c.id = i.municipio_id \n" +
                " INNER JOIN configuracion.sede s ON s.id = i.sede_id \n" +
                " INNER JOIN configuracion.carrera c2 ON c2.id = i.carrera_id\n" +
                " INNER JOIN configuracion.estado_proceso ep ON ep.id = i.estado_proceso_id \n" +
                " INNER JOIN configuracion.estado_contacto ec ON ec.id = i.estado_contacto_id \n" +
                " WHERE i.fecha_creacion::date BETWEEN :fechaInicio AND :fechaFin";

        // Parámetros
        Map<String, Object> params = new HashMap<>();
        params.put("fechaInicio", fechaInicio);
        params.put("fechaFin", fechaFin);

        if (!estadoProcesoIds.isEmpty()) {
            sql += " AND i.estado_proceso_id IN (:estadoProcesoIds)";
            params.put("estadoProcesoIds", estadoProcesoIds);
        }
        if (!estadoContactoIds.isEmpty()) {
            sql += " AND i.estado_contacto_id IN (:estadoContactoIds)";
            params.put("estadoContactoIds", estadoContactoIds);
        }

        // Utiliza la plantilla de parámetros nombrados
        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
            CargarInscripcionResponseDTO dto = new CargarInscripcionResponseDTO();
            dto.setInscripcionId(rs.getInt("inscripcion_id"));
            dto.setTipoIdentificacion(rs.getString("tipo_identificacion"));
            dto.setNombreCompleto(rs.getString("nombre_completo"));
            dto.setCelular(rs.getString("celular"));
            dto.setEmail(rs.getString("email"));
            dto.setUniversidad(rs.getString("universidad"));
            dto.setMunicipio(rs.getString("municipio"));
            dto.setSede(rs.getString("sede"));
            dto.setCarrera(rs.getString("carrera"));
            dto.setEstadoProceso(rs.getString("estado_proceso"));
            dto.setEstadoContacto(rs.getString("estado_contacto"));
            dto.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
            dto.setAnioGraduacion(rs.getInt("anio_graduacion"));
            dto.setSemestreInicioEstudio(rs.getInt("semestre_inicio_estudio"));
            return dto;
        });
    }
}