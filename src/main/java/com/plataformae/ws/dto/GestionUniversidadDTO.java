package com.plataformae.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GestionUniversidadDTO {
    private Long id;
    private String estado;
    private String nombre;
    private String nit;
    private String tipoUniversidad;
    private String usuarioUltimaModificacion;
    private String logoBase64;
    private String terminosCondiciones;
    private String direccion;
    private String telefono;
    private List<RelCarreraUniversidadMunicipioDTO> carreras;

}