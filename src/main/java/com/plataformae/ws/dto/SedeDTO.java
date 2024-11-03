package com.plataformae.ws.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class SedeDTO {
    private Long id;
    private String estado;
    private String nombre;
    private String direccion;
    @JsonIgnore
    private MunicipioDTO municipio;
    private List<CarreraDTO> carreras;

    public SedeDTO(Long id, String estado, String nombre, String direccion, MunicipioDTO municipio, List<CarreraDTO> carreras) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.municipio = municipio;
        this.carreras = carreras;
    }

    public SedeDTO() {

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public MunicipioDTO getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioDTO municipio) {
        this.municipio = municipio;
    }

    public List<CarreraDTO> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<CarreraDTO> carreras) {
        this.carreras = carreras;
    }
}
