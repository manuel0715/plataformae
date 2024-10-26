package com.plataformae.ws.dto;

import java.time.LocalDate;

public class GuardarGestionIncripcionDTO {

    private Integer inscripcionId;
    private Integer estadoProcesoId;
    private Integer estadoContactoId;
    private String comentarioGestion;
    private LocalDate fechaProximaLlamada;
    private String usuarioGestion;

    public Integer getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(Integer inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public Integer getEstadoProcesoId() {
        return estadoProcesoId;
    }

    public void setEstadoProcesoId(Integer estadoProcesoId) {
        this.estadoProcesoId = estadoProcesoId;
    }

    public Integer getEstadoContactoId() {
        return estadoContactoId;
    }

    public void setEstadoContactoId(Integer estadoContactoId) {
        this.estadoContactoId = estadoContactoId;
    }

    public String getComentarioGestion() {
        return comentarioGestion;
    }

    public void setComentarioGestion(String comentarioGestion) {
        this.comentarioGestion = comentarioGestion;
    }

    public LocalDate getFechaProximaLlamada() {
        return fechaProximaLlamada;
    }

    public void setFechaProximaLlamada(LocalDate fechaProximaLlamada) {
        this.fechaProximaLlamada = fechaProximaLlamada;
    }

    public String getUsuarioGestion() {
        return usuarioGestion;
    }

    public void setUsuarioGestion(String usuarioGestion) {
        this.usuarioGestion = usuarioGestion;
    }
}
