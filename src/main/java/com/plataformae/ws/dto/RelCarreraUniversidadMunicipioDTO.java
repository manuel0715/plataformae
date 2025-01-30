package com.plataformae.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelCarreraUniversidadMunicipioDTO {

    private Long id;
    private Long universidadId;   // ID de la universidad
    private String nombreUniversidad;  // Nombre de la universidad
    private Long municipioId;      // ID del municipio
    private Long carreraId;        // ID de la carrera
    private String nombreCarrera;  // Nombre de la carrera
    private String descripcion;    // Descripción de la carrera
    private String pensum;         // URL del pensum
    private BigDecimal valorSemestre;  // Valor del semestre
    private String tituloOtorgado;     // Título otorgado
    private Integer duracion;          // Duración de la carrera
    private Long modalidadId;          // ID de la modalidad

}
