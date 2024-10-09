package com.plataformae.ws.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class SedeDTO {
    private Long id;
    private String estado;
    private String nombre;
    private String direccion;
    @JsonIgnore
    private CiudadDTO ciudad;
    private List<CarreraDTO> carreras;

    public SedeDTO(Long id, String estado, String nombre, String direccion, CiudadDTO ciudad,List<CarreraDTO> carreras) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
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

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }

    public List<CarreraDTO> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<CarreraDTO> carreras) {
        this.carreras = carreras;
    }
}
