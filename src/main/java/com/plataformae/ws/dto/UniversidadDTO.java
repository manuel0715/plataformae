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
    private List<MunicipioDTO> municipios;
    private String logoBase64;
    private String terminosCondiciones;

    public UniversidadDTO(){

    }

    public UniversidadDTO(Long id, String estado, String nombre, String nit, String tipoUniversidad, String logoBase64, String terminosCondiciones) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
        this.nit = nit;
        this.tipoUniversidad = tipoUniversidad;
        this.municipios = new ArrayList<>();
        this.logoBase64=logoBase64;
        this.terminosCondiciones=terminosCondiciones;
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

    public List<MunicipioDTO> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<MunicipioDTO> municipios) {
        this.municipios = municipios;
    }

    public String getLogoBase64() {
        return logoBase64;
    }

    public void setLogoBase64(String logoBase64) {
        this.logoBase64 = logoBase64;
    }

    public String getTerminosCondiciones() {
        return terminosCondiciones;
    }

    public void setTerminosCondiciones(String terminosCondiciones) {
        this.terminosCondiciones = terminosCondiciones;
    }
}
