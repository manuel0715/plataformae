package com.plataformae.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraficasResponseDTO {
    private Integer totalInscripciones;
    private List<GraficasDTO> municipios;
    private List<GraficasDTO> universidades;
    private List<GraficasDTO> estadoProceso;
    private List<GraficasDTO> estadoContacto;
    private List<GraficasDTO> carreras;
}