package com.plataformae.ws.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarreraDTO {
    private Integer municipioId;
    private Long carreraId;
    private Long modalidadId;
    private String descripcion;
    private String pensum;
    private BigDecimal valorSemestre;
    private String tituloOtorgado;
    private Integer duracion;
}
