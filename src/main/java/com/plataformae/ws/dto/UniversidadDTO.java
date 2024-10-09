package com.plataformae.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class UniversidadDTO {

    private Long id;
    private String estado;
    private String nombre;
    private String nit;
    private String tipoUniversidad;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CiudadDTO> ciudades;

    public UniversidadDTO(){

    }

    public UniversidadDTO(Long id, String estado, String nombre, String nit, String tipoUniversidad, List<CiudadDTO> ciudades) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
        this.nit = nit;
        this.tipoUniversidad = tipoUniversidad;
        this.ciudades = ciudades;
    }

    public UniversidadDTO(Long id, String estado, String nombre, String nit, String tipoUniversidad) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
        this.nit = nit;
        this.tipoUniversidad = tipoUniversidad;
        this.ciudades = new ArrayList<>(); // Inicia vacío por defecto si no se pasan ciudades
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTipoUniversidad() {
        return tipoUniversidad;
    }

    public void setTipoUniversidad(String tipoUniversidad) {
        this.tipoUniversidad = tipoUniversidad;
    }

    public List<CiudadDTO> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<CiudadDTO> ciudades) {
        this.ciudades = ciudades;
    }
}
