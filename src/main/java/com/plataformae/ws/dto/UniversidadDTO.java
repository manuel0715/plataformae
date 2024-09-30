package com.plataformae.ws.dto;

import java.util.List;

public class UniversidadDTO {

    private Long id;
    private String estado;
    private String nombre;
    private String nit;
    private String tipoUniversidad;
    private List<SedeDTO> sedes;

    public UniversidadDTO(){

    }

    public UniversidadDTO(Long id, String estado, String nombre, String nit, String tipoUniversidad, List<SedeDTO> sedes) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
        this.nit = nit;
        this.tipoUniversidad = tipoUniversidad;
        this.sedes = sedes;
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

    public List<SedeDTO> getSedes() {
        return sedes;
    }

    public void setSedes(List<SedeDTO> sedes) {
        this.sedes = sedes;
    }
}
