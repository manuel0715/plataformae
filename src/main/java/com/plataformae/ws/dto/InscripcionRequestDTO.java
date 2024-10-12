package com.plataformae.ws.dto;

public class InscripcionRequestDTO {

    private String usuarioId;
    private Integer universidadId;
    private Integer ciudadId;
    private Integer sedeId;
    private Integer carreraId;
    private Integer estadoProcesoId;
    private Integer estadoContactoId;

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getUniversidadId() {
        return universidadId;
    }

    public void setUniversidadId(Integer universidadId) {
        this.universidadId = universidadId;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public Integer getSedeId() {
        return sedeId;
    }

    public void setSedeId(Integer sedeId) {
        this.sedeId = sedeId;
    }

    public Integer getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(Integer carreraId) {
        this.carreraId = carreraId;
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
}
