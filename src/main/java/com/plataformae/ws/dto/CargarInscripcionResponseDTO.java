package com.plataformae.ws.dto;

import java.time.LocalDateTime;

public class CargarInscripcionResponseDTO {

    private Integer inscripcionId;
    private String tipoIdentificacion;
    private String nombres;
    private String apellidos;
    private String celular;
    private String email;
    private String universidad;
    private String ciudad;
    private String sede;
    private String carrera;
    private String estadoProceso;
    private String estadoContacto;
    private LocalDateTime fechaCreacion;

    public CargarInscripcionResponseDTO(Integer inscripcionId, String tipoIdentificacion, String nombres, String apellidos, String celular, String email, String universidad, String ciudad, String sede, String carrera, String estadoProceso, String estadoContacto, LocalDateTime fechaCreacion) {
        this.inscripcionId = inscripcionId;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.email = email;
        this.universidad = universidad;
        this.ciudad = ciudad;
        this.sede = sede;
        this.carrera = carrera;
        this.estadoProceso = estadoProceso;
        this.estadoContacto = estadoContacto;
        this.fechaCreacion = fechaCreacion;
    }

    public CargarInscripcionResponseDTO(Integer inscripcionId,
                                        String universidad,
                                        String ciudad,
                                        String sede,
                                        String carrera,
                                        LocalDateTime fechaCreacion) {
        this.inscripcionId = inscripcionId;
        this.universidad = universidad;
        this.ciudad = ciudad;
        this.sede = sede;
        this.carrera = carrera;
        this.fechaCreacion = fechaCreacion;
    }

    public CargarInscripcionResponseDTO() {

    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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
}
