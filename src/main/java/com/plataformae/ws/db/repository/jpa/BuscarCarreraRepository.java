package com.plataformae.ws.db.repository.jpa;


import com.plataformae.ws.dto.CarreraUniversidadResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.plataformae.ws.util.Utils.convertirBase64;

@Repository
public class BuscarCarreraRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<CarreraUniversidadResponseDTO> buscarCarreras(String query, Integer universidad) {
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
            d.nombre AS departamento,
            c.nombre AS carrera,
            rcum.descripcion,
            rcum.pensum,
            rcum.valor_semestre,
            rcum.titulo_otorgado,
            rcum.duracion,
            rcum.modalidad
        FROM configuracion.rel_carrera_universidad_municipio rcum
        INNER JOIN configuracion.universidad u ON u.id = rcum.universidad_id
        INNER JOIN configuracion.municipio m ON m.id = rcum.municipio_id
        INNER JOIN configuracion.departamento d ON d.id = m.departamento_id
        INNER JOIN configuracion.carrera c ON c.id = rcum.carrera_id
        WHERE 1=1
    """);

        // Mapa de parámetros
        Map<String, Object> params = new HashMap<>();

        // Verificar si 'query' es proporcionado
        if (query != null && !query.trim().isEmpty()) {
            sql.append(" AND (SIMILARITY(rcum.texto_busqueda, :query) > 0.05 OR rcum.texto_busqueda ILIKE '%' || :query || '%')");
            params.put("query", query);
        }

        // Verificar si 'universidad' es proporcionada
        if (universidad != null) {
            sql.append(" AND u.id = :universidad");
            params.put("universidad", universidad);
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
                    convertirBase64(rs.getString("logo")),
                    convertirBase64(rs.getString("terminos_condiciones")),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("municipio"),
                    rs.getString("departamento"),
                    rs.getString("carrera"),
                    rs.getString("descripcion"),
                    convertirBase64(rs.getString("pensum")),
                    rs.getDouble("valor_semestre"),
                    rs.getString("titulo_otorgado"),
                    rs.getString("duracion"),
                    rs.getString("modalidad")
            );


}
