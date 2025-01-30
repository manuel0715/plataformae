package com.plataformae.ws.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscripcionRequestDTO {

    private String usuarioId;
    private Long universidadId;
    private Long municipioId;
    private Long carreraId;
    private Integer estadoProcesoId=6;
    private Integer estadoContactoId=1;
    private Integer anioGraduacion ;
    private Integer semestreInicioEstudio;

}
