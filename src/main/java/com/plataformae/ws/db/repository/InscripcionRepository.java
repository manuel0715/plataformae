package com.plataformae.ws.db.repository;

import com.plataformae.ws.dto.CargarInscripcionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class InscripcionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CargarInscripcionResponseDTO> findInscripciones(LocalDate fechaInicio, LocalDate fechaFin,
                                                                List<Integer> estadoProcesoIds, List<Integer> estadoContactoIds) {
        String sql = "SELECT i.id AS inscripcion_id, \n" +
                " u2.tipo_identificacion,\n" +
                " u2.nombres, \n" +
                " u2.apellidos, \n" +
                " u2.celular, \n" +
                " u2.email, \n" +
                " u.nombre AS universidad,\n" +
                " c.nombre AS ciudad,\n" +
                " s.nombre AS sede,\n" +
                " c2.nombre AS carrera, \n" +
                " ep.nombre AS estado_proceso,\n" +
                " ec.nombre AS estado_contacto,\n" +
                " i.fecha_creacion \n" +
                " FROM gestion_academica.inscripciones i \n" +
                " INNER JOIN administrativo.usuarios u2 ON u2.usuario = i.usuario_id \n" +
                " INNER JOIN configuracion.universidad u ON u.id = i.universidad_id \n" +
                " INNER JOIN configuracion.ciudad c ON c.id = i.ciudad_id \n" +
                " INNER JOIN configuracion.sede s ON s.id = i.sede_id \n" +
                " INNER JOIN configuracion.carrera c2 ON c2.id = i.carrera_id\n" +
                " INNER JOIN configuracion.estado_proceso ep ON ep.id =i.estado_proceso_id \n" +
                " INNER JOIN configuracion.estado_contacto ec ON ec.id=i.estado_contacto_id \n" +
                " WHERE i.fecha_creacion::date BETWEEN ?::date AND ?::date";

        if (!estadoProcesoIds.isEmpty()) {
            sql += " AND i.estado_proceso_id IN (" + String.join(",", Collections.nCopies(estadoProcesoIds.size(), "?")) + ")";
        }
        if (!estadoContactoIds.isEmpty()) {
            sql += " AND i.estado_contacto_id IN (" + String.join(",", Collections.nCopies(estadoContactoIds.size(), "?")) + ")";
        }

        List<Object> params = new ArrayList<>();
        params.add(fechaInicio);
        params.add(fechaFin);
        params.addAll(estadoProcesoIds);
        params.addAll(estadoContactoIds);

        return jdbcTemplate.query(sql, params.toArray(), (rs, rowNum) -> {
            CargarInscripcionResponseDTO dto = new CargarInscripcionResponseDTO();
            dto.setInscripcionId(rs.getInt("inscripcion_id"));
            dto.setTipoIdentificacion(rs.getString("tipo_identificacion"));
            dto.setNombres(rs.getString("nombres"));
            dto.setApellidos(rs.getString("apellidos"));
            dto.setCelular(rs.getString("celular"));
            dto.setEmail(rs.getString("email"));
            dto.setUniversidad(rs.getString("universidad"));
            dto.setCiudad(rs.getString("ciudad"));
            dto.setSede(rs.getString("sede"));
            dto.setCarrera(rs.getString("carrera"));
            dto.setEstadoProceso(rs.getString("estado_proceso"));
            dto.setEstadoContacto(rs.getString("estado_contacto"));
            dto.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
            return dto;
        });
    }
}