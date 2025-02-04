package com.plataformae.ws.dto;

import com.plataformae.ws.db.entity.RelCarreraUniversidadMunicipio;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversidadDTO {

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
    private List<RelCarreraUniversidadMunicipio> carreras;


}
