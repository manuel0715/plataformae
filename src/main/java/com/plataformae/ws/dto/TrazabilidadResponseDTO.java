package com.plataformae.ws.dto;

import java.time.LocalDateTime;

public class TrazabilidadResponseDTO {
    private Integer trazabilidadId;
    private Integer inscripcionId;
    private String estadoProceso;
    private String estadoContacto;
    private LocalDateTime fechaGestion;
    private LocalDateTime fechaProximaLlamada;
    private String comentarioGestion;
    private String usuarioGestion;

    public TrazabilidadResponseDTO(Integer trazabilidadId, Integer inscripcionId, String estadoProceso, String estadoContacto, LocalDateTime fechaGestion, LocalDateTime fechaProximaLlamada, String comentarioGestion, String usuarioGestion) {
        this.trazabilidadId = trazabilidadId;
        this.inscripcionId = inscripcionId;
        this.estadoProceso = estadoProceso;
        this.estadoContacto = estadoContacto;
        this.fechaGestion = fechaGestion;
        this.fechaProximaLlamada = fechaProximaLlamada;
        this.comentarioGestion = comentarioGestion;
        this.usuarioGestion = usuarioGestion;
    }

    public Integer getTrazabilidadId() {
        return trazabilidadId;
    }

    public void setTrazabilidadId(Integer trazabilidadId) {
        this.trazabilidadId = trazabilidadId;
    }

    public Integer getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(Integer inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public String getEstadoContacto() {
        return estadoContacto;
    }

    public void setEstadoContacto(String estadoContacto) {
        this.estadoContacto = estadoContacto;
    }

    public LocalDateTime getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(LocalDateTime fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public LocalDateTime getFechaProximaLlamada() {
        return fechaProximaLlamada;
    }

    public void setFechaProximaLlamada(LocalDateTime fechaProximaLlamada) {
        this.fechaProximaLlamada = fechaProximaLlamada;
    }

    public String getComentarioGestion() {
        return comentarioGestion;
    }

    public void setComentarioGestion(String comentarioGestion) {
        this.comentarioGestion = comentarioGestion;
    }

    public String getUsuarioGestion() {
        return usuarioGestion;
    }

    public void setUsuarioGestion(String usuarioGestion) {
        this.usuarioGestion = usuarioGestion;
    }
}
