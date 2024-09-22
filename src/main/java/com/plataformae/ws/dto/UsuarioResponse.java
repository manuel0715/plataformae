package com.plataformae.ws.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class UsuarioResponse {

    private String identificacion;
    private String estado;
    private String tipoIdentificacion;
    private String nombres;
    private String apellidos;
    private String email;
    private String emailMask;
    private String celular;
    private String tipoUsuario;
    private LocalDateTime fechaCreacion;
    private String username;

    public UsuarioResponse(String identificacion, String estado, String tipoIdentificacion,
                           String nombres, String apellidos, String email, String emailMask, String celular, String tipoUsuario, LocalDateTime fechaCreacion, String username) {
        this.identificacion = identificacion;
        this.estado = estado;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.celular = celular;
        this.tipoUsuario = tipoUsuario;
        this.fechaCreacion = fechaCreacion;
        this.username = username;
        this.emailMask=emailMask;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailMask() {
        return emailMask;
    }

    public void setEmailMask(String emailMask) {
        this.emailMask = emailMask;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
