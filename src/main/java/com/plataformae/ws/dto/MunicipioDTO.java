package com.plataformae.ws.dto;

public class MunicipioDTO {
    private Long id;
    private String estado;
    private String nombre;
    private String codigoPostal;
    private String abreviatura;

    public MunicipioDTO(Long id, String estado, String nombre, String codigoPostal, String abreviatura) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
        this.abreviatura = abreviatura;
    }



    public MunicipioDTO() {

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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

}

