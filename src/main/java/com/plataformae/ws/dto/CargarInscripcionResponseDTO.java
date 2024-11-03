package com.plataformae.ws.dto;

import java.time.LocalDateTime;

public class CargarInscripcionResponseDTO {

    private Integer inscripcionId;
    private String tipoIdentificacion;
    private String nombreCompleto;
    private String celular;
    private String email;
    private String universidad;
    private String municipio;
    private String sede;
    private String carrera;
    private String estadoProceso;
    private String estadoContacto;
    private LocalDateTime fechaCreacion;
    private Integer anioGraduacion ;
    private Integer semestreInicioEstudio;

    public CargarInscripcionResponseDTO(Integer inscripcionId, String tipoIdentificacion, String nombreCompleto, String apellidos, String celular, String email, String universidad, String municipio, String sede, String carrera, String estadoProceso, String estadoContacto, LocalDateTime fechaCreacion, Integer anioGraduacion, Integer semestreInicioEstudio) {
        this.inscripcionId = inscripcionId;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombreCompleto = nombreCompleto;
        this.celular = celular;
        this.email = email;
        this.universidad = universidad;
        this.municipio = municipio;
        this.sede = sede;
        this.carrera = carrera;
        this.estadoProceso = estadoProceso;
        this.estadoContacto = estadoContacto;
        this.fechaCreacion = fechaCreacion;
        this.anioGraduacion = anioGraduacion;
        this.semestreInicioEstudio = semestreInicioEstudio;
    }

    public CargarInscripcionResponseDTO() {

    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(Integer inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getAnioGraduacion() {
        return anioGraduacion;
    }

    public void setAnioGraduacion(Integer anioGraduacion) {
        this.anioGraduacion = anioGraduacion;
    }

    public Integer getSemestreInicioEstudio() {
        return semestreInicioEstudio;
    }

    public void setSemestreInicioEstudio(Integer semestreInicioEstudio) {
        this.semestreInicioEstudio = semestreInicioEstudio;
    }
}
