package com.plataformae.ws.db.repository;


import com.plataformae.ws.dto.CarreraUniversidadResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BuscarCarreraRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<CarreraUniversidadResponseDTO> buscarCarreras(String query, Integer universidad, Integer modalidad,Integer  municipio) {
        StringBuilder sql = new StringBuilder("""
        SELECT
            u.id AS universidad_id,
            u.nombre AS universidad,
            u.nit,
            u.tipo_universidad,
            u.logo,
            u.terminos_condiciones,
            u.direccion,
            u.telefono,
            m.nombre AS municipio,
            m.id AS municipio_id,
            d.nombre AS departamento,
            c.nombre AS carrera,
            c.id AS carrera_id,
            rcum.descripcion,
            rcum.pensum,
            rcum.valor_semestre,
            rcum.titulo_otorgado,
            rcum.duracion,
            mo.nombre as modalidad,
            mo.color as color_modalidad
        FROM configuracion.rel_carrera_universidad_municipio rcum
        INNER JOIN configuracion.universidad u ON u.id = rcum.universidad_id
        INNER JOIN configuracion.municipio m ON m.id = rcum.municipio_id
        INNER JOIN configuracion.departamento d ON d.id = m.departamento_id
        INNER JOIN configuracion.carrera c ON c.id = rcum.carrera_id
        INNER JOIN configuracion.modalidad mo ON mo.id=rcum.modalidad_id
        WHERE 1=1
    """);

        // Mapa de parámetros
        Map<String, Object> params = new HashMap<>();

        // Verificar si 'query' es proporcionado
        if (query != null && !query.trim().isEmpty()) {
            sql.append(" AND (SIMILARITY(rcum.texto_busqueda, :query) > 0.1 OR rcum.texto_busqueda ILIKE '%' || :query || '%')");
            params.put("query", query);
        }

        // Verificar si 'universidad' es proporcionada
        if (universidad != null) {
            sql.append(" AND u.id = :universidad");
            params.put("universidad", universidad);
        }

        if (modalidad != null) {
            sql.append(" AND mo.id = :modalidad");
            params.put("modalidad", modalidad);
        }

        if (municipio != null) {
            sql.append(" AND m.id = :municipio");
            params.put("municipio", municipio);
        }

        // Ordenar por similitud de búsqueda, si se proporcionó 'query'
        if (query != null && !query.trim().isEmpty()) {
            sql.append(" ORDER BY SIMILARITY(rcum.texto_busqueda, :query) DESC");
        }


        return jdbcTemplate.query(sql.toString(), params, ROW_MAPPER);
    }

    private static final RowMapper<CarreraUniversidadResponseDTO> ROW_MAPPER = (rs, rowNum) ->
            new CarreraUniversidadResponseDTO(
                    rs.getLong("universidad_id"),
                    rs.getString("universidad"),
                    rs.getString("nit"),
                    rs.getString("tipo_universidad"),
                    rs.getString("logo"),
                    rs.getString("terminos_condiciones"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("municipio"),
                    rs.getInt("municipio_id"),
                    rs.getString("departamento"),
                    rs.getString("carrera"),
                    rs.getInt("carrera_id"),
                    rs.getString("descripcion"),
                    rs.getString("pensum"),
                    rs.getDouble("valor_semestre"),
                    rs.getString("titulo_otorgado"),
                    rs.getString("duracion"),
                    rs.getString("modalidad"),
                    rs.getString("color_modalidad")
            );


}
