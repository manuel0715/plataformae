package com.plataformae.ws.dto;


import java.util.List;

public class AuthResponseDTO {

    private String identificacion;
    private String tipoIdentificacion;
    private String nombres;
    private String apellidos;
    private String tipoUsuario;
    private String username;
    private String token;
    private List<MenuPadreDTO> menu;

    public List<MenuPadreDTO> getMenuPadre() {
        return menu;
    }

    public void setMenuPadre(List<MenuPadreDTO> menuPadre) {
        this.menu = menuPadre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String toke) {
        this.token = toke;
    }
}
